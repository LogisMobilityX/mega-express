package com.express.adapter.output.persistence.jpa.user;

import com.express.application.port.output.user.UserProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserReader, UserProcessor {

    private final UserSpringDataJpaRepository userSpringDataJpaRepository;

    @Override
    public void saveUser(User user) {
        //Jpa Entity로 변환 과정 필요

    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public User updateUserById(Long id, User user) {
        //JpaEntity 변환 과정이 필요함
        return null;
    }

    @Override
    public Map<String, Object> login(String email, String password) {
        return Map.of();
    }

    @Override
    public String logOut(String email, String token) {
        return "";
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
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
