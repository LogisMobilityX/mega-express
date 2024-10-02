package com.express.application.port.input.user;

import com.express.application.port.input.user.request.CertifiedEmailRequest;

import java.util.Map;

public interface UserAuthUseCase {



    Map<String,Object> login();

    String logOut();
    void sendCertifiedEmail(String email);

    String certifiedEmail(CertifiedEmailRequest certifiedEmailRequest);
}
