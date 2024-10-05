package com.express.adapter.input.rest.user.request;

import com.express.application.port.input.user.ModifyUserCommand;
import com.express.domain.model.user.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.parameters.P;

public class ModifyUserRequest {
    //이메일
    private String email;

    //유저등급
    private UserGrade userGrade;

    //유저 이름
    private String userName;

    //전화번호
    private String phoneNumber;

    //회사
    //private Company company;

    //권한 JSON
    //private String userAuth;

    public ModifyUserCommand toModifyUserCommand() {
        return ModifyUserCommand.builder()
                .email(Email.from(email))
                .phoneNumber(PhoneNumber.from(phoneNumber))
                .userGrade(userGrade)
                .username(UserName.from(userName))
                .build();
    }
}
