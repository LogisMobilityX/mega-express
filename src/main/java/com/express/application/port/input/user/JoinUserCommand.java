package com.express.application.port.input.user;

import com.express.domain.model.user.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JoinUserCommand {
    private Email email;
    private UserName username;
    private UserGrade userGrade;
    private Password password;
    private PhoneNumber phoneNumber;
    private boolean certifiedEmail;


    public JoinUserCommand(@NotNull Email email,
                           @NotNull UserName username,
                           @NotNull Password password,
                           @NotNull UserGrade userGrade,
                           @NotNull PhoneNumber phoneNumber,
                           @NotNull boolean certifiedEmail) {
        if (certifiedEmail == false){
            throw new RuntimeException();
        }
        this.email = email;
        this.username = username;
        this.userGrade = userGrade;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.certifiedEmail = certifiedEmail;
    }

}
