package com.express.adapter.input.rest.user.request;

import com.express.application.port.input.user.WithdrawalUserCommand;
import com.express.domain.model.user.Email;
import com.express.domain.model.user.Password;
import com.express.domain.model.user.UserGrade;

public class WithdrawalUserRequest {
    private String email;
    private String password;

    public WithdrawalUserCommand toWithdrawalUserCommand() {
        return WithdrawalUserCommand.builder()
                .email(Email.from(email))
                .password(Password.from(password))
                .build();
    }
}
