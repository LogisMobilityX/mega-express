package com.project.match.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class UserBase {
    @Id
    private Long id;
}
