package com.express.application.port.output.inmemory.redis;

import java.util.concurrent.TimeUnit;

public interface CacheProcessor {
    void setValues(String key, Object value);                       // 값 등록 / 수정

    void setValues(String key, Object value, TimeUnit timeUnit, long timeout);    // 값 등록 / 수정

    Object getValue(String key);                                    // 값 조회

    boolean deleteValue(String key);
}
