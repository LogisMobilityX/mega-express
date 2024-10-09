package com.express.adapter.input.rest.user.request;

import com.express.application.port.input.user.LoginUserCommand;
import com.express.domain.model.user.Email;
import com.express.domain.model.user.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequest {
    private String email;
    private String password;

    public LoginUserCommand toLoginUserCommand() {
        return LoginUserCommand.builder()
                .email(Email.from(email))
                .password(Password.from(password))
                .build();
    }
}
