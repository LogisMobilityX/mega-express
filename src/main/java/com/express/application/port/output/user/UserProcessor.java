package com.express.application.port.output.user;

import com.express.application.dto.user.UserInfoCreateDTO;
import com.express.application.dto.user.UserInfoSelectDTO;
import com.express.application.dto.user.UserInfoUpdateDTO;
import com.express.domain.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserProcessor {
    void saveUser(UserInfoCreateDTO.UserCreateDto user);
    void deleteUserById(Long id);
    User updateUserById(Long id, UserInfoUpdateDTO user);

    Map<String,Object> login(String email , String password);

    String logOut(String email,String token);

    Map<String,Object> addAuthorization(String email, String token, Map<String,Object> authorization);
    Map<String,Object> deleteAuthorization(String email, String token, Map<String,Object> authorization);
}
