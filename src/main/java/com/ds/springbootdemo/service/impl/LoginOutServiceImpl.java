package com.ds.springbootdemo.service.impl;

import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.LoginOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Rich_man
 * @title: LoginOutServiceImpl
 * @date 2022/3/13  18:07
 * @description:
 */
@Service
public class LoginOutServiceImpl implements LoginOutService {

    @Autowired
    RedisTemplate<Object, User> redisTemplate;

    @Override
    public Result logOut(String token) {
        redisTemplate.delete("token"+token);
        return Result.success(null);
    }
}
