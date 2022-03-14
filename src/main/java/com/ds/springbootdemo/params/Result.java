package com.ds.springbootdemo.params;

import javafx.beans.binding.ObjectExpression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rich_man
 * @title: Result
 * @date 2022/3/11  15:47
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String message;

    private Object data;

    public static Result success(Object data){
        return new Result(true,200,"成功",data);
    }

    public static Result fail(String message,int code){
        return new Result(false,code,message,null);
    }
}
