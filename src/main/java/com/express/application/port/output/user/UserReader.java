package com.express.application.port.output.user;

import com.express.domain.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReader {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
