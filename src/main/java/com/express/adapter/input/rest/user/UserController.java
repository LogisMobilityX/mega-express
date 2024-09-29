package com.express.adapter.input.rest.user;

import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserReadUseCase userReadUseCase;
    private final UserProcessorUseCase userProcessorUseCase;

    public void saveUser(){
        //userProcessorUseCase.createUser();
    }

    public void readUser(){}
    public void deleteUser(){}
    public void updateUser(){}
    public void loginUser(){}
    public void logoutUser(){}
    public void addAuthorization(){}
    public void deleteAuthorization(){}

}
