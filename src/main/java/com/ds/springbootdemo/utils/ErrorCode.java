package com.ds.springbootdemo.utils;

import lombok.Data;

/**
 * @author Rich_man
 * @title: ErrorCode
 * @date 2022/3/11  15:51
 * @description:
 */

public enum ErrorCode {

    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    ACCOUNT_EXIST(10004,"该用户已经存在"),
    TOKEN_ERROR(10003,"token异常"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),;

    private String msg;
    private int code;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
