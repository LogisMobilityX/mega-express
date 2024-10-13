package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.response.AuthenticatedResponse;
import com.express.adapter.input.rest.user.response.ReissueAccessTokenResponse;
import com.express.adapter.input.rest.user.response.UserCertificatedResponse;
import com.express.domain.model.user.Email;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserAuthUseCase {

    AuthenticatedResponse login(LoginUserCommand loginUserCommand);

    ReissueAccessTokenResponse reissueAccessToken(String refreshToken);

    void logOut(UserDetails userDetails, HttpServletRequest request);
    void sendCertifiedEmail(Email email);

    UserCertificatedResponse certifiedEmail(CertifiedEmailCommand certifiedEmailRequest);
}
