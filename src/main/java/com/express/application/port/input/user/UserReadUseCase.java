package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.response.ReadUserResponse;

import java.util.Optional;


public interface UserReadUseCase {
    ReadUserResponse findByEmail(String email);
    ReadUserResponse findById(Long userId);

}
