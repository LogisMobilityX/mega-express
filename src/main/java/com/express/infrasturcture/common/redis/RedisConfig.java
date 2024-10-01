package com.express.infrasturcture.common.redis;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {
    private String redisHost;
    private int redisPort;


    public LettuceConnectionFactory lettuceConnectionFactory(){
        return new LettuceConnectionFactory(redisHost,redisPort);
    }
}
