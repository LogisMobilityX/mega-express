package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.request.CertifiedEmailRequest;
import com.express.domain.model.user.Email;

import java.util.Map;


public interface UserAuthUseCase {

    Map<String,Object> login();

    String logOut();
    void sendCertifiedEmail(Email email);

    boolean certifiedEmail(CertifiedEmailCommand certifiedEmailRequest);
}
