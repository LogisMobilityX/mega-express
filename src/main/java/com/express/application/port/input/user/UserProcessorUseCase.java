package com.express.application.port.input.user;

import com.express.application.port.input.user.request.CertifiedEmailRequest;
import com.express.application.port.input.user.request.JoinUserRequest;
import com.express.application.port.input.user.request.ModifyUserRequest;

import java.util.Map;

public interface UserProcessorUseCase {
    //user 생성
    void createUser(JoinUserRequest joinUserRequest);
    //user 정보 수정
    ModifyUserRequest updateUserInfo(ModifyUserRequest userUpdateDto);
    //user 삭제
    String deleteUserInfo(Long userId);

}


