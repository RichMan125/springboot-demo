package com.ds.springbootdemo.utils;

import com.ds.springbootdemo.pojo.User;

/**
    * @title: UserThreadLocal
    * @author Rich_man
    * @date 2022/3/13  19:28
    * @description:
    */
public class UserThreadLocal {
    private UserThreadLocal(){}

    public static final ThreadLocal<User> LOCAL=new InheritableThreadLocal<>();


    public static void put(User user){
        LOCAL.set(user);
    }

    public static User get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
