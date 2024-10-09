package com.express.adapter.output.persistence.jpa.user;

import com.express.application.port.output.user.UserAuthProcessor;
import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.domain.model.user.User;
import com.express.adapter.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserReader, UserProcessor, UserAuthProcessor {

    private final UserSpringDataJpaRepository userSpringDataJpaRepository;
    private final UserProcessorMapper userProcessorMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public void saveUser(User user) {
        //Jpa Entity로 변환 과정 필요
        UserJpaEntity userJpaEntity = userProcessorMapper.mapToJpaEntity(user);
        userSpringDataJpaRepository.save(userJpaEntity);
    }

    @Override
    public boolean withdrawalUserById(Long userId) {
        try {
            userSpringDataJpaRepository.deleteById(userId);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public void modifyUserInfo(Long id, User user) {
        UserJpaEntity existUserInfo = userSpringDataJpaRepository
                .findById(id)
                .orElseThrow();
        if (!isEmpty(existUserInfo)){
            existUserInfo.modifyUserInfo(user);
        }else{
            //유저가 존재하지 않는다 예외
        }
    }

    @Override
    public Map<String, Object> addAuthorization(String email, String token, Map<String, Object> authorization) {
        return Map.of();
    }

    @Override
    public Map<String, Object> deleteAuthorization(String email, String token, Map<String, Object> authorization) {
        return Map.of();
    }

    @Override
    public User UserInfoById(Long userId) {
        User user = userReadMapper.mapToUserModel(userSpringDataJpaRepository.findById(userId).orElseThrow());
        return user;
    }

    @Override
    public User UserInfoByEmail(String email) {
        User user = userReadMapper.mapToUserModel(userSpringDataJpaRepository.findByEmail(email));
        return user;
    }

    @Override
    public String UserPasswordByUserId(String email) {
        return "";
    }


}
