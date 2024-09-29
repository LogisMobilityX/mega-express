package com.express.adapter.output.persistence.jpa.user;

import com.express.application.port.output.persistence.user.UserProcessor;
import com.express.application.port.output.persistence.user.UserReader;
import com.express.domain.model.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserReader, UserProcessor {

    private final UserSpringDataJpaRepository userSpringDataJpaRepository;

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public User updateUserById(Long id, User user) {
        return null;
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
