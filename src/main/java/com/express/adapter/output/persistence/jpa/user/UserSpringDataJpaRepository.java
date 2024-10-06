package com.express.adapter.output.persistence.jpa.user;

import com.express.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringDataJpaRepository extends JpaRepository<UserJpaEntity, Long> {
//    @Query(nativeQuery = true, value = "")
//    Optional<UserJpaEntity> findPasswordById(String email);

}
