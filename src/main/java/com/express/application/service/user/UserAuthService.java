package com.express.application.service.user;

import com.express.adapter.common.UseCase;
import com.express.adapter.common.security.TokenInfo;
import com.express.adapter.input.rest.user.response.AuthenticatedResponse;
import com.express.adapter.input.rest.user.response.ReissueAccessTokenResponse;
import com.express.adapter.input.rest.user.response.UserCertificatedResponse;
import com.express.application.port.input.user.CertifiedEmailCommand;
import com.express.application.port.input.user.LoginUserCommand;
import com.express.application.port.input.user.UserAuthUseCase;
import com.express.application.port.output.email.EmailSender;
import com.express.application.port.output.inmemory.redis.CacheProcessor;
import com.express.application.port.output.security.SecurityProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.domain.model.user.Email;
import com.express.domain.model.user.SecurityCustomUser;
import com.express.domain.model.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class UserAuthService implements UserAuthUseCase, UserDetailsService {
    @Value("${jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    private final UserReader userReader;
    private final SecurityProcessor securityProcessor;
    private final CacheProcessor cacheProcessor;
    private final EmailSender emailSender;

    @Override
    public AuthenticatedResponse login(LoginUserCommand loginUserCommand) {
        TokenInfo tokenInfo = securityProcessor.setAuthentication(
                loginUserCommand.getEmail().getEmailText(),
                loginUserCommand.getPassword().getPasswordText());

        //refresh token 저장
        cacheProcessor.setValues(
                loginUserCommand.getEmail().getEmailText()
                ,tokenInfo.getRefreshToken()
                ,TimeUnit.MILLISECONDS
                ,accessExpirationTime
        );

        User user = userReader.UserInfoByEmail(loginUserCommand.getEmail().getEmailText());

        return AuthenticatedResponse.builder()
                .tokenInfo(tokenInfo)
                .user(user)
                .build();
    }

    @Override
    public ReissueAccessTokenResponse reissueAccessToken(String refreshToken) {
        if (securityProcessor.validateToken(refreshToken)){
            Authentication authentication = securityProcessor.getAuthentication(refreshToken);
            String getRefreshTokenInCache = String.valueOf(cacheProcessor.getValue(authentication.getName()));
            //레디스에 저장된 토큰값과 일치하는지 검증
            if (!isEmpty(getRefreshTokenInCache) && refreshToken.equals(getRefreshTokenInCache)){
                //일치하면 엑세스토큰 재발급
                String accessToken = securityProcessor.createAccessToken(authentication);

                //securityProcessor.createAccessToken()
                return ReissueAccessTokenResponse.reissueAccessTokenResponse(accessToken);
            }else{
                throw new RuntimeException("Refresh token mismatch");
            }
            //일치하지 않으면 예외
        }else{
            throw new RuntimeException("Token Expires,Requires login");
        }
    }

    @Override
    public void logOut(UserDetails userDetails, HttpServletRequest request) {
        if(isEmpty(cacheProcessor.getValue(userDetails.getUsername()))){
            throw new RuntimeException("User not found");
        }else{
            cacheProcessor.deleteValue(userDetails.getUsername());
        }
    }

    @Override
    public void sendCertifiedEmail(Email email) {
        //인증번호 생성
        int authorizationCode = randomAuthorizationCode();

        // 보내기 전 redis에 1차 저장
        cacheProcessor.setValues(email.getEmailText(), authorizationCode, TimeUnit.SECONDS, 300);

        //이메일 전송
        emailSender.sendCertifiedCode(email.getEmailText(), authorizationCode);

        log.info("Succeeded to send Email");

    }

    @Override
    public UserCertificatedResponse certifiedEmail(CertifiedEmailCommand certifiedEmailRequest) {
        /*
        레디스에서 Email값으로 인증 번호 가지고 와서 요청으로 받은 코드와 비교
        다르면 false
        같으면 true
         */
        Object value = cacheProcessor.getValue(certifiedEmailRequest.getEmail().getEmailText());
        log.info("Certified code in redis : {}", value);
        boolean certifiedStatus = certifiedEmailRequest.compareCertifiedCode(value);

        return UserCertificatedResponse.of(certifiedStatus);
    }

    private int randomAuthorizationCode() {
        Random random = new Random();
        int authCode = random.nextInt(900000) + 100000;
        return authCode;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO : UsernameNotFountException 예외처리 확인
        User user = userReader.UserInfoByEmail(username);

        return new SecurityCustomUser(user);
    }

}
