package com.ds.springbootdemo.utils;

import com.ds.springbootdemo.params.Result;

/**
 * @author Rich_man
 * @title: ReturnCheck
 * @date 2022/3/11  17:40
 * @description:
 */
public class ReturnCheck {

    public static Result returnCheck(Object obj){
        if (null !=obj){
            return new Result(true,200,"获取成功",obj);
        }else{
            return new Result(false,404,"获取失败",null);
        }
    }
}
