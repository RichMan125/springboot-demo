package com.ds.springbootdemo.config;

import com.ds.springbootdemo.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Rich_man
 * @title: MvcWebConfig
 * @date 2022/3/11  16:54
 * @description:
 */
@Configuration
public class MvcWebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/export")
                .excludePathPatterns("/user/import")
                .addPathPatterns("/user/**")
                .addPathPatterns("/loginOut")
                .addPathPatterns("/upload");

    }

    /**
    *  @author: rich_man
    *  @Description:
     *  跨域配置
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
