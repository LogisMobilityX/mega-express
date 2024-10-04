package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.response.ReadUserResponse;

import java.util.Optional;


public interface UserReadUseCase {
    Optional<ReadUserResponse> findByEmail(String email);
    Optional<ReadUserResponse> findById(String id);

}
