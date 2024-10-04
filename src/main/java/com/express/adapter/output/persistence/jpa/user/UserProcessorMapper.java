package com.express.adapter.output.persistence.jpa.user;

import com.express.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserProcessorMapper {
    public static  UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(
                user.getPhoneNumber().getPhoneNumberText(),
                user.getUserName().getUserNameText(),
                user.getPassword().getPasswordText(),
                user.getUserGrade(),
                user.getEmail().getEmailText()
        );
    }
}
