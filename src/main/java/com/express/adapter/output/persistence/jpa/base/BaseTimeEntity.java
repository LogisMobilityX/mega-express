package com.express.adapter.output.persistence.jpa.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseTimeEntity {

    @CreatedDate //생성시간
    @Column(name = "created_date", updatable = false) //insertable=false는 insert 시점에 막는 것이고, updatable는 update 시점에 막는 기능입니다.
    private LocalDateTime createdDate;

    @LastModifiedDate //변경시간
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}
