package com.express.application.port.input.user;

import com.express.application.dto.user.UserInfoCreateDTO.UserCreateDto;
import com.express.application.dto.user.UserInfoSelectDTO.UserUpdateRequestDTO;
import com.express.application.dto.user.UserInfoSelectDTO.UserUpdateResponseDTO;

import java.util.Map;

public interface UserProcessorUseCase {

    //user 생성
    void createUser(UserCreateDto userCreateDto);
    //user 정보 수정
    UserUpdateResponseDTO updateUserInfo(UserUpdateRequestDTO userUpdateDto);
    //user 삭제
    String deleteUserInfo(Long userId);


    Map<String,Object> login();

    String logOut();
}


