package com.express.adapter.input.rest.user;

import com.express.application.port.input.user.UserAuthUseCase;
import com.express.application.port.input.user.request.CertifiedEmailRequest;
import com.express.application.port.input.user.request.LoginUserRequest;
import com.express.infrasturcture.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /*
     * 이메일 인증은 회원 정보가 없는 상태에서 진행 된다.
     * 이메일로 랜덤코드 발급 -> 인증하기
     */
    @PostMapping(value = "/certifiedEmail/send")
    public ResponseEntity<Void> sendCertifiedEmail(@RequestBody String email){
        userAuthUseCase.sendCertifiedEmail(email);
        return ResponseEntity.noContent().build();
    }

    /*
     * 이메일 인증 로직
     * key - email , value - randomCode 로 redis 에 저장
     * 조회 후 검증
     * 검증 완료했다면 redis 에서 삭제 후 true 반환
     * 검증 실패했다면 false 반환
     * 프론트는 이 코드를 항상 false로 유지하고 인증 성공하면 true로 바꾼 다음 회원가입 진행
     * false로 넘어오면 회원가입 불가
     */
    @PostMapping("/certifiedEmail")
    public boolean certifiedEmail(CertifiedEmailRequest certifiedEmailRequest){

        return userAuthUseCase.certifiedEmail(certifiedEmailRequest);

    }

}
