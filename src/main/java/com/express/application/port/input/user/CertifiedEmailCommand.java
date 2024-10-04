package com.express.application.port.input.user;

import com.express.domain.model.user.Email;
import lombok.Builder;

import static org.springframework.util.ObjectUtils.isEmpty;

@Builder
public class CertifiedEmailCommand {
    private Email email;
    private String certifiedCode;

    public boolean compareCertifiedCode(Object value) {
        if (isEmpty(value)) {
            return false;
        }
        if (this.certifiedCode.equals(String.valueOf(value))) {
            return true;
        }
        return false;
    }

    public Email email() {
        return email;
    }


}
