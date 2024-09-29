package com.express.application.port.output.persistence.user;

import com.express.domain.model.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProcessor {
    void saveUser(User user);
    void deleteUserById(Long id);
    User updateUserById(Long id, User user);
}
