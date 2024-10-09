package com.express.application.service.user;

import com.express.adapter.common.UseCase;
import com.express.adapter.common.security.TokenInfo;
import com.express.adapter.input.rest.user.response.AuthenticatedResponse;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class UserAuthService implements UserAuthUseCase, UserDetailsService {
    private final UserReader userReader;
    private final SecurityProcessor securityProcessor;
    private final CacheProcessor cacheProcessor;
    private final EmailSender emailSender;

    @Override
    public AuthenticatedResponse login(LoginUserCommand loginUserCommand) {
        TokenInfo tokenInfo = securityProcessor.setAuthentication(
                loginUserCommand.getEmail().getEmailText(),
                loginUserCommand.getPassword().getPasswordText());

        User user = userReader.UserInfoByEmail(loginUserCommand.getEmail().getEmailText());

        return AuthenticatedResponse.builder()
                .tokenInfo(tokenInfo)
                .user(user)
                .build();
    }

    @Override
    public String logOut() {
        return "";
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
    public boolean certifiedEmail(CertifiedEmailCommand certifiedEmailRequest) {
        /*
        레디스에서 Email값으로 인증 번호 가지고 와서 요청으로 받은 코드와 비교
        다르면 false
        같으면 true
         */
        Object value = cacheProcessor.getValue(certifiedEmailRequest.getEmail().getEmailText());
        log.info("Certified code in redis : {}", value);
        return certifiedEmailRequest.compareCertifiedCode(value);
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
