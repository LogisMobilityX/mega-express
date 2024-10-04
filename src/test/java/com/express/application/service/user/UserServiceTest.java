package com.express.application.service.user;

import com.express.application.port.input.user.UserAuthUseCase;
import com.express.application.port.input.user.request.CertifiedEmailRequest;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {
    private final Logger logger = Logger.getLogger(UserServiceTest.class.getName());

    @Autowired
    UserAuthUseCase userAuthUseCase;

    @Test
    @DisplayName(value = "인증번호 발급 테스트")
    void sendCertifiedEmail() {
        userAuthUseCase.sendCertifiedEmail("rdj10149@gmail.com");
    }

    @Test
    @DisplayName(value = "이메일 인증 테스트")
    void certifiedEmail() {

        CertifiedEmailRequest certifiedEmailRequest = new CertifiedEmailRequest("rdj10149@gmail.com","251849");
        boolean certifiedEmail = userAuthUseCase.certifiedEmail(certifiedEmailRequest);
        MatcherAssert.assertThat(certifiedEmail,is(equalTo(true)));
        logger.info("Certified email returned: " + certifiedEmail);

    }
    @Test
    void createUser() {
    }

    @Test
    void updateUserInfo() {
    }

    @Test
    void deleteUserInfo() {
    }





    @Test
    void findByEmail() {
    }

    @Test
    void findById() {
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