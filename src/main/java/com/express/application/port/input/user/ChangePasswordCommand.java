package com.express.application.port.input.user;

import com.express.domain.model.user.Password;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ChangePasswordCommand {

    @Getter
    private Password password;

    public ChangePasswordCommand(Password password) {
        this.password = password;
    }
}
