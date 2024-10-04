package com.express.application.port.output.email;

public interface EmailSender {
    void sendCertifiedCode(String email,int certifiedCode);
}
