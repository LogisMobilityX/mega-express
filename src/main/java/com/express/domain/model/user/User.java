package com.express.domain.model.user;


import com.express.domain.model.EventModel;
import com.express.infrasturcture.aggregate.AggregateRoot;
import com.express.infrasturcture.event.DomainEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends EventModel {
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
