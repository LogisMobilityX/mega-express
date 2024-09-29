package com.express.application.port.input.user;

import com.express.application.dto.user.UserInfoCreateDTO.UserCreateDto;
import com.express.application.dto.user.UserInfoSelectDTO.UserUpdateRequestDTO;
import com.express.application.dto.user.UserInfoSelectDTO.UserUpdateResponseDTO;

import java.lang.foreign.ValueLayout;
import java.util.Map;

public interface UserCreateUseCase {

    //user 생성
    void createUser(UserCreateDto userCreateDto);
    //user 정보 수정
    UserUpdateResponseDTO updateUserInfo(UserUpdateRequestDTO userUpdateDto);
    //user 삭제
    String deleteUserInfo(Long userId);
    //user 조회

    Map<String,Object> login();
    //유저별 권한 처리

    String logOut();


}


