package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.request.CertifiedEmailRequest;
import com.express.adapter.input.rest.user.response.AuthenticatedResponse;
import com.express.domain.model.user.Email;

import java.util.Map;


public interface UserAuthUseCase {

    AuthenticatedResponse login(LoginUserCommand loginUserCommand);

    String logOut();
    void sendCertifiedEmail(Email email);

    boolean certifiedEmail(CertifiedEmailCommand certifiedEmailRequest);
}
