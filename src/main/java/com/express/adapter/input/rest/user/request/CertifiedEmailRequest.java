package com.express.adapter.input.rest.user.request;

import com.express.application.port.input.user.CertifiedEmailCommand;
import com.express.domain.model.user.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
public class CertifiedEmailRequest {
    private String email;
    private String certifiedCode;

    public boolean compareCertifiedCode(Object value) {
        if (isEmpty(value)){
            return false;
        }
        if (this.certifiedCode.equals(String.valueOf(value))){
            return true;
        }
        return false;
    }

    public String email() {
        return email;
    }

    public CertifiedEmailCommand toCertifiedEmailCommand() {
        return CertifiedEmailCommand.builder()
                .email(Email.from(email))
                .certifiedCode(certifiedCode)
                .build();
    }


}
