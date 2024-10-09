package com.express.adapter.output.persistence.jpa.user;

import com.express.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringDataJpaRepository extends JpaRepository<UserJpaEntity, Long> {

}
