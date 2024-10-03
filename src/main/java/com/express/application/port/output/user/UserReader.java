package com.express.application.port.output.user;

import com.express.domain.model.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserReader {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
