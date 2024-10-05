package com.express.application.port.input.user;

import com.express.domain.model.user.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ModifyUserCommand {
    //이메일
    private Email email;

    //유저등급
    private UserGrade userGrade;

    //유저 이름
    private UserName username;

    //전화번호
    private PhoneNumber phoneNumber;

    //회사
    //private Company company;

    //권한 JSON
    //private String userAuth;


    public ModifyUserCommand(@NotNull Email email,
                             @NotNull UserGrade userGrade,
                             @NotNull UserName username,
                             @NotNull PhoneNumber phoneNumber) {
        this.email = email;
        this.userGrade = userGrade;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
