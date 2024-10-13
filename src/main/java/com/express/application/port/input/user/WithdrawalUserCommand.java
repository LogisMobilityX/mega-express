package com.express.application.port.input.user;

import com.express.domain.model.user.Email;
import com.express.domain.model.user.Password;
import com.express.domain.model.user.UserId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WithdrawalUserCommand {
    private Email email;
    private Password password;

    public WithdrawalUserCommand(@NotNull Email email,@NotNull Password password) {
        this.email = email;
        this.password = password;
    }
}
