package com.express.application.service.user;


import com.express.adapter.output.persistence.jpa.user.UserPersistenceAdapter;
import com.express.application.dto.user.UserInfoCreateDTO;
import com.express.application.dto.user.UserInfoSelectDTO;
import com.express.application.port.input.user.UserProcessorUseCase;
import com.express.application.port.input.user.UserReadUseCase;
import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.application.service.messaging.MessagePublisher;
import com.express.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/*
 * 유저 생성
 * 유저 정보 수정
 * 유저 탈퇴
 * 유저 조회
 * 로그인
 * 로그아웃
 *
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserProcessorUseCase, UserReadUseCase {
    private UserProcessor userProcessor;
    private UserReader userReader;
    private final MessagePublisher messagePublisher;
    /**
     *
     * @param userCreateDto
     */
    @Override
    public void createUser(UserInfoCreateDTO.UserCreateDto userCreateDto) {

     //   userPersistenceAdapter.saveUser();
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

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }
}
