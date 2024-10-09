package com.express.adapter.input.rest.user;

import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import com.express.application.port.input.user.request.CertifiedEmailRequest;
import com.express.application.port.input.user.request.JoinUserRequest;
import com.express.application.port.input.user.request.ModifyUserRequest;
import com.express.application.port.input.user.request.WithdrawalUserRequest;
import com.express.infrasturcture.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController(value = "/v1/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserReadUseCase userReadUseCase;
    private final UserProcessorUseCase userProcessorUseCase;

    @PostMapping(value = "/join")
    public void saveUser(@RequestBody JoinUserRequest joinUserRequest){
        //validate (유효한 전화번호, 이메일 형식, 이메일 인증 여부 )
        //가입
    }
    /**
     *
     */
    @GetMapping(value = "/info")
    public void readUser(@PathVariable Long userId){
        //유저 아이디 검증
    }

    @DeleteMapping(value = "/withdrawal")
    public void deleteUser(WithdrawalUserRequest withdrawalUserRequest){
        //로그인 된 상태 유저 ID를 가지고 와서 탈퇴
    }

    @PutMapping(value = "/modify")
    public void updateUser(ModifyUserRequest modifyUserRequest){
        //로그인 된 유저 아이디를 가지고 오고
        //수정Form을 받아서 수정
    }




}
