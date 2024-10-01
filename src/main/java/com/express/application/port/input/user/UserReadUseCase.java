package com.express.application.port.input.user;

import com.express.application.port.input.user.response.ReadUserResponse;
import com.express.domain.model.user.User;

import java.util.Optional;

public interface UserReadUseCase {
    Optional<ReadUserResponse> findByEmail(String email);
    Optional<ReadUserResponse> findById(String id);

}
