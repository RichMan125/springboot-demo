package com.ds.springbootdemo.controller;

import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rich_man
 * @title: RegisterController
 * @date 2022/3/13  13:13
 * @description:
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;


    @PostMapping("")
    public Result register(@RequestBody User user){
        return userService.addUser(user);
    }

}
