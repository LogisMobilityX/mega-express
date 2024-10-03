package com.express.application.port.output.user;

import com.express.domain.model.user.User;

import java.util.Map;
public interface UserProcessor {
    void saveUser(User user);
    void deleteUserById(Long id);
    User updateUserById(Long id, User user);

    Map<String,Object> login(String email , String password);

    String logOut(String email,String token);

    Map<String,Object> addAuthorization(String email, String token, Map<String,Object> authorization);
    Map<String,Object> deleteAuthorization(String email, String token, Map<String,Object> authorization);
}
