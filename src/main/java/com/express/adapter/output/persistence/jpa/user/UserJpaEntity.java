package com.express.adapter.output.persistence.jpa.user;

import com.express.adapter.output.persistence.jpa.base.BaseTimeEntity;
import com.express.domain.model.user.User;
import com.express.domain.model.user.UserGrade;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;


/**
 *도메인을 중심으로 하는 애플리케이션을 개발할 때 등장하는 개념으로 애그리거트가 있다.
 * 애그리거트(Aggregate)란 관련된 객체들을 모아둔 하나의 단위로, 값 객체(Value Object)와 엔티티(Entity)로 구성된다.
 *  그리고 애그리거트의 중심에는 애그리거트 루트가 존재하는데, 이를 애그리거트 루트(Aggregate Root)라고 부른다.
 * 객체들은 애그리거트 루트를 중심으로 관리된다. (해당 포스팅은 이러한 내용을 자세히 다루는 것이 주목적이 아니므로 간략히만 살펴보도록 하자.)
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserJpaEntity extends BaseTimeEntity {
    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //JPA 에서 기본키 값을 자동으로 생성할 때 사용하는 옵션
    private Long id;

    //이메일
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;
    //유저등급
    @Column(name = "user_grade")
    private UserGrade userGrade;
    //패스워드
    @Column(name = "password")
    private String password;

    //전화번호
    @Column(name = "phone_number")
    private String phoneNumber;

    public UserJpaEntity(String email, String username, UserGrade userGrade, String password, String phoneNumber) {

        this.email = email;
        this.username = username;
        this.userGrade = userGrade;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void modifyUserInfo(User user){
        this.email = user.getEmail().getEmailText();
        this.userGrade = user.getUserGrade();
        this.phoneNumber = user.getPhoneNumber().getPhoneNumberText();
        this.username = user.getUserName().getUserNameText();
    }
    //회사
    //private Company company;

    //권한 JSON
    //private String userAuth;


}
