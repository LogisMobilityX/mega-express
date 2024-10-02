package com.express.application.port.output.inmemory.redis;

import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public interface RedisProcessor {
    void setValues(String key, String value);                       // 값 등록 / 수정

    void setValues(String key, String value, long timeout);    // 값 등록 / 수정

    Object getValue(String key);                                    // 값 조회

    boolean deleteValue(String key);
}
