package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.request.JoinUserRequest;
import com.express.adapter.input.rest.user.request.ModifyUserRequest;

public interface UserProcessorUseCase {
    //user 생성
    void joinUser(JoinUserCommand joinUserRequest);
    //user 정보 수정
    ModifyUserRequest updateUserInfo(ModifyUserCommand userUpdateDto);
    //user 삭제
    String deleteUserInfo(WithdrawalUserCommand withdrawalUserCommand);

}


