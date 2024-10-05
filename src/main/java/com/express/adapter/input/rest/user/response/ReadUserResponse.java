package com.express.adapter.input.rest.user.response;

import lombok.Builder;

public class ReadUserResponse {
    private String email;
    private String userGrade;
    private String username;
    private String phoneNumber;
    private boolean certifiedEmail;

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
