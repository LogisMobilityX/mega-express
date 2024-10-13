package com.express.application.port.input.user;

import com.express.adapter.input.rest.user.request.JoinUserRequest;
import com.express.adapter.input.rest.user.request.ModifyUserRequest;

public interface UserProcessorUseCase {
    //user 생성
    void joinUser(JoinUserCommand joinUserRequest);
    //user 정보 수정
    void modifyUserInfo(ModifyUserCommand userUpdateDto);
    //user 삭제
    boolean withdrawalUser(WithdrawalUserCommand withdrawalUserCommand);

}


