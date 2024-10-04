package com.express.adapter.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int redisPort;

    /*
    redis 연결을 위한 connection 생성
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(redisHost,redisPort);
    }


    /**
     * Redis 데이터 처리를 위한 템플릿을 구성합니다.
     * 해당 구성된 RedisTemplate을 통해서 데이터 통신으로 처리되는 대한 직렬화를 수행.
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        // Redis를 연결합니다.
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // Key-Value 형태로 직렬화를 수행.
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // Hash Key-Value 형태로 직렬화를 수행.
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        // 기본적으로 직렬화를 수행.
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
