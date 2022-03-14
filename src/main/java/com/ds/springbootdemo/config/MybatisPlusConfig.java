package com.ds.springbootdemo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rich_man
 * @title: MybatisPlusConfig
 * @date 2022/3/11  16:21
 * @description:
 */
@Configuration
@MapperScan("com.ds.springbootdemo.mapper")
public class MybatisPlusConfig {

    /**
    *  @author: rich_man
    *  @Description:
     *  配置Mybatis-plus分页插件
    */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
