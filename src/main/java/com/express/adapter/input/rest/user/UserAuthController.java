package com.express.adapter.input.rest.user;

import com.express.application.port.input.user.UserAuthUseCase;
import com.express.application.port.input.user.request.LoginUserRequest;
import com.express.infrasturcture.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController(value = "/v1/api/user/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthUseCase userAuthUseCase;
    @PostMapping(path = "/login")
    public void loginUser(LoginUserRequest loginUserRequest){
        //validate
        //로그인

    }

    @PostMapping(path = "/logout")
    public void logoutUser(){
        //validate
    }
    @PostMapping(path = "/add")
    public void addAuthorization(){}

    @DeleteMapping(path = "/delete")
    public void deleteAuthorization(){}
}
