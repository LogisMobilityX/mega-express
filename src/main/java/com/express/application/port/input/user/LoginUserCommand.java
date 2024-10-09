package com.express.application.port.input.user;

import com.express.domain.model.user.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserCommand {
    private Email email;
    private Password password;


    public LoginUserCommand(@NotNull Email email,
                           @NotNull Password password) {
        this.email = email;
        this.password = password;
    }
}
