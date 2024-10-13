package com.express.application.service.user;

import com.express.adapter.input.rest.user.request.JoinUserRequest;
import com.express.adapter.input.rest.user.response.ReadUserResponse;
import com.express.adapter.input.rest.user.response.UserCertificatedResponse;
import com.express.application.port.input.user.*;
import com.express.adapter.input.rest.user.request.CertifiedEmailRequest;
import com.express.domain.model.user.*;
import net.minidev.json.JSONUtil;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class UserServiceTest {
    private final Logger logger = Logger.getLogger(UserServiceTest.class.getName());

    @Autowired UserAuthUseCase userAuthUseCase;
    @Autowired UserProcessorUseCase userProcessorUseCase;
    @Autowired UserReadUseCase userReadUseCase;

    @Test
    @DisplayName(value = "인증번호 발급 테스트")
    void sendCertifiedEmail() {
        //todo 수정
        Email email = Email.from("rdj10149@gmail.com");
        userAuthUseCase.sendCertifiedEmail(email);
    }

    @Test
    @DisplayName(value = "이메일 인증 테스트")
    void certifiedEmail() {
        //todo 수정
        Email email = Email.from("rdj10149@gmail.com");
        CertifiedEmailCommand certifiedEmailCommand = new CertifiedEmailCommand(email,"251849");
        UserCertificatedResponse userCertificatedResponse = userAuthUseCase.certifiedEmail(certifiedEmailCommand);
        MatcherAssert.assertThat(userCertificatedResponse.isCertificationStatus(),is(equalTo(true)));
        logger.info("Certified email returned: " + userCertificatedResponse.isCertificationStatus());

    }
    @Test
    @DisplayName(value = "유저 회원가입 테스트")
    void createUser() {
        JoinUserCommand joinUserCommand = JoinUserCommand.builder()
                .email(Email.from("rdj10149@naver.com"))
                .userGrade(UserGrade.MASTER)
                .username(UserName.from("김철수"))
                .password(Password.from("Eassword001!"))
                .phoneNumber(PhoneNumber.from("010-0000-0000"))
                .certifiedEmail(true)
                .build();
        userProcessorUseCase.joinUser(joinUserCommand);
    }

    @Test
    @DisplayName(value = "유저 정보 수정 테스트")
    void updateUserInfo() {
        ModifyUserCommand modifyUserCommand = ModifyUserCommand.builder()
                .username(UserName.from("김철수"))
                .userGrade(UserGrade.ADMIN)
                .phoneNumber(PhoneNumber.from("010-1111-1111"))
                .email(Email.from("rdj10149@naver.com"))
                .build();
        userProcessorUseCase.modifyUserInfo(modifyUserCommand);
    }

    @Test
    @DisplayName(value = "유저 회원 탈퇴 테스트")
    void deleteUserInfo() {
        WithdrawalUserCommand withdrawalUserCommand = WithdrawalUserCommand.builder()
                .password(Password.from("Eassword001!"))
                .build();

        userProcessorUseCase.withdrawalUser(withdrawalUserCommand);
    }
    @Test
    @DisplayName(value = "유저 회원 정보 조회")
    void findById() {
        ReadUserResponse userResponse = userReadUseCase.findById(7L);

        System.out.println(userResponse.getEmail());
        System.out.println(userResponse.getUsername());
        System.out.println(userResponse.getUserGrade());
        System.out.println(userResponse.getPhoneNumber());
    }

    @Test
    void findByEmail() {
    }

    @Test
    void login() {
    }

    @Test
    void logOut() {
    }

    @Test
    void getEmailContent() {
    }
}