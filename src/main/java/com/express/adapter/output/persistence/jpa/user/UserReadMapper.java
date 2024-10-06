package com.express.adapter.output.persistence.jpa.user;

import com.express.domain.model.user.*;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper {
    User mapToUserModel(UserJpaEntity user) {
        return User.builder()
                .userId(UserId.from(user.getId()))
                .email(Email.from(user.getEmail()))
                .userName(UserName.from(user.getUsername()))
                .phoneNumber(PhoneNumber.from(user.getPhoneNumber()))
                .password(Password.from(user.getPassword()))
                .userGrade(user.getUserGrade())
                .build();
    }


}
