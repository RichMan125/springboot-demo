package com.ds.springbootdemo.handler;

import com.ds.springbootdemo.params.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rich_man
 * @title: AllExectionHandler
 * @date 2022/3/12  10:28
 * @description:
 */
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class AllExectionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result exectionHandler(Exception e){
        e.printStackTrace();
        return Result.fail("系统异常",-999);
    }

}
