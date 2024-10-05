package com.express.application.port.output.user;

import com.express.domain.model.user.User;

import java.util.Map;
public interface UserProcessor {
    void saveUser(User user);
    boolean withdrawalUserById(Long userId);
    void modifyUserInfo(Long id, User user);


}
