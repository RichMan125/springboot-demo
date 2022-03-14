package com.ds.springbootdemo.config;

import com.ds.springbootdemo.pojo.User;
import javafx.beans.binding.ObjectExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author Rich_man
 * @title: MyRedisConfig
 * @date 2022/3/13  15:52
 * @description:
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, User> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,User> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<User>(User.class);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
