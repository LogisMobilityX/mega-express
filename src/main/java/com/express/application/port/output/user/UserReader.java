package com.express.application.port.output.user;

import com.express.adapter.output.persistence.jpa.user.UserJpaEntity;
import com.express.domain.model.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserReader {
    User UserInfoById(Long id);
    User UserInfoByEmail(String email);

    String UserPasswordByUserId(String email);
}
