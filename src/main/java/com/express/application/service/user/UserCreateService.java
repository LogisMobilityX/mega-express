package com.express.application.service.user;

import com.express.application.dto.user.UserInfoCreateDTO;
import com.express.application.dto.user.UserInfoSelectDTO;
import com.express.application.port.input.user.UserCreateUseCase;

public class UserCreateService implements UserCreateUseCase {
    @Override
    public void createUser(UserInfoCreateDTO.UserCreateDto userCreateDto) {

    }

    @Override
    public UserInfoSelectDTO.UserUpdateResponseDTO updateUserInfo(UserInfoSelectDTO.UserUpdateRequestDTO userUpdateDto) {
        return null;
    }

    @Override
    public String deleteUserInfo(Long userId) {
        return "";
    }
}
