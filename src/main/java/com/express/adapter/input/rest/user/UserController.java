package com.express.adapter.input.rest.user;

import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import com.express.application.port.input.user.request.CertifiedEmailRequest;
import com.express.application.port.input.user.request.JoinUserRequest;
import com.express.application.port.input.user.request.ModifyUserRequest;
import com.express.application.port.input.user.request.WithdrawalUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/certifiedEmail/send")
    public String sendCertifiedEmail(String email){
        /*
         * 이메일 인증은 회원 정보가 없는 상태에서 진행 된다.
         * 이메일로 랜덤코드 발급 -> 인증하기
         */
        return "";
    }

    @PostMapping("/certifiedEmail")
    public boolean certifiedEmail(CertifiedEmailRequest certifiedEmailRequest){
        /*
         * 이메일 인증 로직
         * key - email , value - randomCode 로 redis 에 저장
         * 조회 후 검증
         * 검증 완료했다면 redis 에서 삭제 후 true 반환
         * 검증 실패했다면 false 반환
         * 프론트는 이 코드를 항상 false로 유지하고 인증 성공하면 true로 바꾼 다음 회원가입 진행
         * false로 넘어오면 회원가입 불가
         */
        return false;
    }



}
