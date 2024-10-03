package com.express.adapter.output.inmemory.redis;

import com.express.application.port.output.inmemory.redis.RedisProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisAdapter implements RedisProcessor {
    private final RedisTemplate<String,String> redisTemplate;
    @Override
    public void setValues(String key, Object value) {
        ValueOperations<String, String> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(key,String.valueOf(value));
        log.info("sucessfully set value to redis");
    }

    @Override
    public void setValues(String key, Object value,TimeUnit timeUnit,long timeout) {
        ValueOperations<String, String> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(key,String.valueOf(value));
        redisTemplate.expire(key,timeout, timeUnit);
        log.info("sucessfully set value to redis");
    }

    @Override
    public Object getValue(String key) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        Object object = opsForValue.get(key);
        return object;
    }

    @Override
    public boolean deleteValue(String key) {
        return redisTemplate.delete(key);
    }
}
