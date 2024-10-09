package com.express.adapter.input.rest.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadUserResponse {
    private String email;
    private String userGrade;
    private String username;
    private String phoneNumber;
    private boolean certifiedEmail;

    //추후 회사 정보 추가

    @Builder
    public ReadUserResponse(String email,
                            String userGrade,
                            String username,
                            String phoneNumber,
                            boolean certifiedEmail) {
        this.email = email;
        this.userGrade = userGrade;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.certifiedEmail = certifiedEmail;
    }
}
