package com.ds.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.LoginService;
import com.ds.springbootdemo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Rich_man
 * @title: LoginServiceImpl
 * @date 2022/3/13  16:56
 * @description:
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    RedisTemplate<Object,User> redisTemplate;


    @Override
    public User checkToken(String token) {
        //判断是否为空
        if (StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap==null){
            return null;
        }

        User user = redisTemplate.opsForValue().get("token" + token);
        if (user==null){
            return null;
        }

        return user;
    }
}
