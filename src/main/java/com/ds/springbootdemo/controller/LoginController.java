package com.ds.springbootdemo.controller;

import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.params.UserVo;
import com.ds.springbootdemo.params.vo.UserParams;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rich_man
 * @title: LoginController
 * @date 2022/3/13  11:01
 * @description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public Result login(@RequestBody UserParams user){
        return userService.checkUser(user);
    }
}
