package com.express.application.service.user;


import com.express.adapter.common.UseCase;
import com.express.adapter.input.rest.user.request.ModifyUserRequest;
import com.express.adapter.input.rest.user.response.ReadUserResponse;
import com.express.application.port.input.user.*;
import com.express.application.port.output.email.EmailSender;
import com.express.application.port.output.inmemory.redis.CacheProcessor;
import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.application.service.messaging.MessagePublisher;
import com.express.domain.model.user.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    private final UserProcessor userProcessor;
    private final UserReader userReader;
    private final CacheProcessor cacheProcessor;
    private final MessagePublisher messagePublisher;
    private final EmailSender emailSender;

    @Override
    public void joinUser(JoinUserCommand joinUserRequest) {
        //user 객체로 변환
        userProcessor.saveUser(joinUserRequest.toUser());
    }

    @Override
    public ModifyUserRequest updateUserInfo(ModifyUserCommand userUpdateDto) {
        return null;
    }

    @Override
    public String deleteUserInfo(WithdrawalUserCommand withdrawalUserCommand) {
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
        Object value = cacheProcessor.getValue(certifiedEmailRequest.email().getEmailText());
        log.info("Certified code in redis : {}", value);
        return certifiedEmailRequest.compareCertifiedCode(value);
    }

    @Override
    public Optional<ReadUserResponse> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<ReadUserResponse> findById(String id) {
        return Optional.empty();
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
