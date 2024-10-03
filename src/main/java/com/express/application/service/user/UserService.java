package com.express.application.service.user;


import com.express.application.port.input.user.UserAuthUseCase;
import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import com.express.application.port.input.user.request.CertifiedEmailRequest;
import com.express.application.port.input.user.request.JoinUserRequest;
import com.express.application.port.input.user.request.ModifyUserRequest;
import com.express.application.port.input.user.response.ReadUserResponse;
import com.express.application.port.output.inmemory.redis.RedisProcessor;
import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.application.service.messaging.MessagePublisher;
import com.express.infrasturcture.common.UseCase;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

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
    private final RedisProcessor redisProcessor;
    private final MessagePublisher messagePublisher;
    private final JavaMailSender javaMailSender;

    @Override
    public void createUser(JoinUserRequest joinUserRequest) {
    }

    @Override
    public ModifyUserRequest updateUserInfo(ModifyUserRequest userUpdateDto) {
        return null;
    }

    @Override
    public String deleteUserInfo(Long userId) {
        return "";
    }

    @Override
    public void sendCertifiedEmail(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject("로지모 인증 번호"); // 메일 제목
            mimeMessageHelper.setText(getEmailContent(email), true); // 메일 본문 내용, HTML 여부
            // 보내기 전 redis에 1차 저장
            //redisProcessor.setValues(email,getEmailContent(email),360);

            //전송
            javaMailSender.send(mimeMessage);

            log.info("Succeeded to send Email");
        } catch (Exception e) {
            log.info("Failed to send Email");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String certifiedEmail(CertifiedEmailRequest certifiedEmailRequest) {
        //레디스에서 Email값으로 인증 번호 가지고 옴

        //인증 번호 검증
        return "";
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
    public String getEmailContent(String email){
        //HTML 형식으로 할지
        Random random = new Random();
        int authCode = random.nextInt(900000) + 100000;
        //그냥 text로 할지
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        .auth-code {\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"text-align: center;\">\n" +
                "        <h2>이메일 인증 코드</h2>\n" +
                "        <p>아래의 인증 코드를 사용하여 이메일 인증을 완료하세요:</p>\n" +
                "        <p class=\"auth-code\">" + authCode + "</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

}
