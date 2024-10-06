package com.express.adapter.output.persistence.jpa.user;

import com.express.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserProcessorMapper {
      UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(
                user.getEmail().getEmailText(),
                user.getUserName().getUserNameText(),
                user.getUserGrade(),
                user.getPassword().getPasswordText(),
                user.getPhoneNumber().getPhoneNumberText()
        );
    }
}
