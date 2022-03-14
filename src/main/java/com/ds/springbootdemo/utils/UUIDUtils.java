package com.ds.springbootdemo.utils;


import java.util.UUID;

/**
 * @author Rich_man
 * @title: UUIDUtils
 * @date 2022/3/12  13:13
 * @description:
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").substring(0,4);
    }
}
