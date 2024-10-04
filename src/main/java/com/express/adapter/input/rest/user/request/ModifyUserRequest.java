package com.express.adapter.input.rest.user.request;

import com.express.domain.model.user.UserGrade;

public class ModifyUserRequest {
    //이메일
    private String email;

    //유저등급
    private UserGrade userGrade;

    //패스워드
    private String password;

    //전화번호
    private String phoneNumber;

    //회사
    //private Company company;

    //권한 JSON
    private String userAuth;
}
