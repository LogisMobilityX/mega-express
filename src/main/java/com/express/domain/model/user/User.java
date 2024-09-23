package com.express.domain.model.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //JPA 에서 기본키 값을 자동으로 생성할 때 사용하는 옵션
    private Long id;

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
