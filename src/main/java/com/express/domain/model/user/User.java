package com.express.domain.model.user;


import com.express.domain.model.EventBase;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class User extends EventBase {
    private UserId userId;
    //이메일
    private Email email;

    //유저 이름
    private UserName userName;

    //유저등급
    private UserGrade userGrade;

    //패스워드
    private Password password;

    //전화번호
    private PhoneNumber phoneNumber;

    //회사
    //private Company company;

    //권한 JSON
    //private String userAuth;
    @Builder
    public User(UserId userId,
                Email email,
                Password password,
                UserName userName,
                UserGrade userGrade,
                PhoneNumber phoneNumber) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userGrade = userGrade;

    }

    @Builder
    public User(Email email,
                UserName userName,
                UserGrade userGrade,
                Password password,
                PhoneNumber phoneNumber) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userGrade = userGrade;

    }


    public User(Email email,
                UserName userName,
                UserGrade userGrade,
                PhoneNumber phoneNumber) {
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userGrade = userGrade;

    }


}
