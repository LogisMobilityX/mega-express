package com.express.application.port.input.user;

import com.express.domain.model.user.User;

import java.util.Optional;

public interface UserReadUseCase {
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);

}
