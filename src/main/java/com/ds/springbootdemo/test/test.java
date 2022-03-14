package com.ds.springbootdemo.test;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Rich_man
 * @title: test
 * @date 2022/3/11  17:36
 * @description:
 */
public class test {
    public static void main(String[] args) {

        String s = DigestUtils.md5Hex("1234");
        System.out.println(s.length());
    }
}
