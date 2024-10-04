package com.express.adapter.input.rest.user.request;

import com.express.application.port.input.user.JoinUserCommand;
import com.express.domain.model.user.Email;
import com.express.domain.model.user.Password;
import com.express.domain.model.user.PhoneNumber;
import com.express.domain.model.user.UserName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class JoinUserRequest {

    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean certifiedEmail;

    public JoinUserCommand toJoinUserCommand() {
        return JoinUserCommand.builder()
                .email(Email.from(email))
                .username(UserName.from(username))
                .password(Password.from(password))
                .phoneNumber(PhoneNumber.from(phoneNumber))
                .certifiedEmail(certifiedEmail)
                .build();
    }
}
