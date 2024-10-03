package com.express.application.port.input.user.request;

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

    public String getEmail() {
        return email;
    }

}
