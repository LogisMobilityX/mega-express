package com.express.adapter.input.rest.user;

import com.express.adapter.input.rest.responseTemplate.CustomResponse;
import com.express.adapter.input.rest.user.response.AuthenticatedResponse;
import com.express.application.port.input.user.UserAuthUseCase;
import com.express.adapter.input.rest.user.request.CertifiedEmailRequest;
import com.express.adapter.input.rest.user.request.LoginUserRequest;
import com.express.adapter.common.WebAdapter;
import com.express.domain.model.user.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping(value = "/v1/api/user/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthUseCase userAuthUseCase;
    @PostMapping( "/login")
    public AuthenticatedResponse loginUser(@RequestBody LoginUserRequest loginUserRequest){
        AuthenticatedResponse login = userAuthUseCase.login(loginUserRequest.toLoginUserCommand());
        return login;
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
        userAuthUseCase.sendCertifiedEmail(Email.from(email));
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
    public boolean certifiedEmail(@RequestBody CertifiedEmailRequest certifiedEmailRequest){

        return userAuthUseCase.certifiedEmail(certifiedEmailRequest.toCertifiedEmailCommand());

    }

}
