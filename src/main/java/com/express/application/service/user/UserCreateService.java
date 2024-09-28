package com.express.application.service.user;

import com.express.application.dto.user.UserInfoCreateDTO;
import com.express.application.dto.user.UserInfoSelectDTO;
import com.express.application.port.input.user.UserCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
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

    @Override
    public Map<String, Object> login() {
        return Map.of();
    }

    @Override
    public String logOut() {
        return "";
    }
}
