package com.express.adapter.input.rest.user;

import com.express.adapter.common.WebAdapter;
import com.express.adapter.input.rest.responseTemplate.ResultDataResponse;
import com.express.adapter.input.rest.user.request.JoinUserRequest;
import com.express.adapter.input.rest.user.request.ModifyUserRequest;
import com.express.adapter.input.rest.user.request.WithdrawalUserRequest;
import com.express.adapter.input.rest.user.response.ReadUserResponse;
import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping(value = "/v1/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserReadUseCase userReadUseCase;
    private final UserProcessorUseCase userProcessorUseCase;

    @PostMapping(value = "/join")
    public void saveUser(@RequestBody JoinUserRequest joinUserRequest){
        //validate (유효한 전화번호, 이메일 형식, 이메일 인증 여부 )
        userProcessorUseCase.joinUser(joinUserRequest.toJoinUserCommand());
    }
    /**
     *
     */
    @GetMapping(value = "/info/{userId}")
    public ReadUserResponse readUser(@PathVariable("userId") Long userId){
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context);
        ReadUserResponse userResponse = userReadUseCase.findById(userId);
        return userResponse;
        //유저 아이디 검증
    }

    @DeleteMapping(value = "/withdrawal")
    public void deleteUser(@RequestBody WithdrawalUserRequest withdrawalUserRequest){
        //로그인 된 유저 아이디를 가지고 오고
        userProcessorUseCase.withdrawalUser(withdrawalUserRequest.toWithdrawalUserCommand());
    }

    @PutMapping(value = "/modify")
    public void updateUser(@RequestBody ModifyUserRequest modifyUserRequest){
        //수정Form을 받아서 수정
        userProcessorUseCase.modifyUserInfo(modifyUserRequest.toModifyUserCommand());
    }




}
