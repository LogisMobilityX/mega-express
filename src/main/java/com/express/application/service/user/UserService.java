package com.express.application.service.user;


import com.express.adapter.common.UseCase;
import com.express.adapter.input.rest.user.request.ModifyUserRequest;
import com.express.adapter.input.rest.user.response.ReadUserResponse;
import com.express.adapter.output.persistence.jpa.user.UserJpaEntity;
import com.express.adapter.output.persistence.jpa.user.UserReadMapper;
import com.express.application.port.input.user.*;
import com.express.application.port.output.email.EmailSender;
import com.express.application.port.output.inmemory.redis.CacheProcessor;
import com.express.application.port.output.user.UserAuthProcessor;
import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.application.service.messaging.MessagePublisher;
import com.express.domain.model.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/*
 * 유저 생성
 * 유저 정보 수정
 * 유저 탈퇴
 * 유저 조회
 * 로그인
 * 로그아웃
 *
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UserService implements UserProcessorUseCase,
        UserReadUseCase,
        UserAuthUseCase {
    //유저 관련
    private final UserProcessor userProcessor;
    private final UserAuthProcessor userAuthProcessor;
    private final UserReader userReader;

    private final CacheProcessor cacheProcessor;
    private final MessagePublisher messagePublisher;
    private final EmailSender emailSender;


    @Override
    public void joinUser(JoinUserCommand joinUserRequest) {
        //user 객체로 변환
        User user = User.builder()
                .userId(UserId.from(0L))
                .email(Email.from(joinUserRequest.getEmail().getEmailText()))
                .userName(UserName.from(joinUserRequest.getUsername().getUserNameText()))
                .userGrade(joinUserRequest.getUserGrade())
                .password(Password.from(joinUserRequest.getPassword().getPasswordText()))
                .phoneNumber(PhoneNumber.from(joinUserRequest.getPhoneNumber().getPhoneNumberText()))
                .build();
        userProcessor.saveUser(user);
    }

    @Override
    public void modifyUserInfo(Long userId, ModifyUserCommand modifyUserCommand) {
        //
        //user 객체로 변환
        User user = User.builder()
                .userId(UserId.from(0L))
                .email(Email.from(modifyUserCommand.getEmail().getEmailText()))
                .userName(UserName.from(modifyUserCommand.getUsername().getUserNameText()))
                .userGrade(modifyUserCommand.getUserGrade())
                .phoneNumber(PhoneNumber.from(modifyUserCommand.getPhoneNumber().getPhoneNumberText()))
                .build();
        userProcessor.modifyUserInfo(userId, user);
    }

    @Override
    public boolean withdrawalUser(Long userId, WithdrawalUserCommand withdrawalUserCommand) {
        //id로 유저 정보 조회
        User user = userReader.UserInfoById(userId);
        //패스워드 검증
        String inputPassword = withdrawalUserCommand.getPassword().getPasswordText();
        if (user.getPassword().comparePassword(inputPassword)){
            // 삭제
            userProcessor.withdrawalUserById(userId);
            return true;
        }else{
            log.info("user withdrawal failed");
            throw new RuntimeException();
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

    @Override
    public ReadUserResponse findByEmail(String email) {
        return null;
    }

    @Override
    public ReadUserResponse findById(Long userId) {
        User user = userReader.UserInfoById(userId);
        return ReadUserResponse.builder()
                .email(user.getEmail().getEmailText())
                .username(user.getUserName().getUserNameText())
                .userGrade(user.getUserGrade().name())
                .phoneNumber(user.getPhoneNumber().getPhoneNumberText())
                .certifiedEmail(true)
                .build();
    }

    @Override
    public Map<String, Object> login() {
        return Map.of();
    }

    @Override
    public String logOut() {
        return "";
    }

    private int randomAuthorizationCode() {
        Random random = new Random();
        int authCode = random.nextInt(900000) + 100000;
        return authCode;
    }


}
