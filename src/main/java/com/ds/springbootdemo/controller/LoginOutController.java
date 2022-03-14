package com.ds.springbootdemo.controller;

import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.service.LoginOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rich_man
 * @title: LoginOutController
 * @date 2022/3/13  18:05
 * @description:
 */
@RestController
@RequestMapping("/loginOut")
public class LoginOutController {

    @Autowired
    LoginOutService loginOutService;


    @GetMapping("")
    public Result logOut(@RequestHeader("token") String token){
        return loginOutService.logOut(token);
    }

}
