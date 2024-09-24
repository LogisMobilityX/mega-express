package com.express.application.port.output.persistence.user;

import com.express.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    void saveUser(User user);
    void deleteUserById(Long id);
    User updateUserById(Long id, User user);
}
