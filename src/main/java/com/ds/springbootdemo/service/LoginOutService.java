package com.ds.springbootdemo.service;

import com.ds.springbootdemo.params.Result;

/**
 * @author Rich_man
 * @title: LoginOutService
 * @date 2022/3/13  18:06
 * @description:
 */
public interface LoginOutService {


    /**
    *  @author: rich_man
    *  @Description:
     *  用户退出
    */
    Result logOut(String token);
}
