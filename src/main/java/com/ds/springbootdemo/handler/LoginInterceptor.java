package com.ds.springbootdemo.handler;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.LoginService;
import com.ds.springbootdemo.utils.ErrorCode;
import com.ds.springbootdemo.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于登录拦截
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1.判断是否访问的为Controller中的方法
         * 2.判断token是否为空，空，则为未登录，非空，则进行token验证
         * 3.如果认证成功，放行即可
         */
        if (!(handler instanceof HandlerMethod)){
            return true;//如果访问的为资源而非Controller中的方法，则进行放行
        }
        String token=request.getHeader("token");

        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        //2
        if (StringUtils.isBlank(token)){
            Result result = Result.fail( ErrorCode.NO_LOGIN.getMsg(),ErrorCode.NO_LOGIN.getCode());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //3
        User user = loginService.checkToken(token);
        if (null==user){
            Result result = Result.fail( ErrorCode.NO_LOGIN.getMsg(),ErrorCode.NO_LOGIN.getCode());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
