package com.express.adapter.output.inmemory.redis;

import com.express.application.port.output.inmemory.redis.RedisProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class RedisAdapter implements RedisProcessor {
    private final RedisTemplate<String,Object> redisTemplate;
    @Override
    public void setValues(String key, String value) {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(key,value);
        log.info("sucessfully set value to redis");
    }

    @Override
    public void setValues(String key, String value,long timeout) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key,value);
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
        log.info("sucessfully set value to redis");
    }

    @Override
    public Object getValue(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object object = opsForValue.get(key);
        return object;
    }

    @Override
    public boolean deleteValue(String key) {
        return redisTemplate.delete(key);
    }
}
