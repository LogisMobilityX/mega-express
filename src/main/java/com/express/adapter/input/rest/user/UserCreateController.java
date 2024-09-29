package com.express.adapter.input.rest.user;


import com.express.application.port.input.user.UserCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/api/user")
@RequiredArgsConstructor
public class UserCreateController {

    private final UserCreateUseCase userCreateUseCase;

    //
    public String createUser(){
        //userCreateUseCase.createUser();
        return null;
    }
}
