package com.express.application.port.output.persistence.user;

import com.express.domain.model.user.User;

import java.util.Optional;

public interface UserReader {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
