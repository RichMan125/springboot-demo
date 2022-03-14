package com.ds.springbootdemo.service;

import com.ds.springbootdemo.pojo.User;

/**
 * @author Rich_man
 * @title: LoginSerivce
 * @date 2022/3/13  16:55
 * @description:
 */
public interface LoginService {


    /**
    *  @author: rich_man
    *  @Description:
     *  进行token的验证
    */
     User checkToken(String token);
}
