package com.express.domain.model.user;


import com.express.domain.model.EventBase;
import com.express.domain.model.user.event.UserCreateEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends EventBase {
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
