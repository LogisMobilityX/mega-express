package com.express.adapter.input.rest.user;

import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import com.express.application.port.input.user.request.JoinUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserReadUseCase userReadUseCase;
    private final UserProcessorUseCase userProcessorUseCase;

    public void saveUser(){
        //userProcessorUseCase.createUser();
    }


    @PostMapping(path = "/join")
    public void readUser(JoinUserRequest joinUserRequest){}
    public void deleteUser(){}
    public void updateUser(){}
    public void loginUser(){}
    public void logoutUser(){}
    public void addAuthorization(){}
    public void deleteAuthorization(){}

}
