package com.osyangxin.moji.component.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 21:09
*/
@Configuration
public class RedisTemplateConfigure {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate returnRedisTemplate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
