package com.ds.springbootdemo.service.impl;

import com.ds.springbootdemo.mapper.FileMapper;
import com.ds.springbootdemo.pojo.File;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.FileService;
import com.ds.springbootdemo.service.UserService;
import com.ds.springbootdemo.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rich_man
 * @title: FileServiceImpl
 * @date 2022/3/14  9:07
 * @description:
 */
@Service
public class FileServiceImpl implements FileService {

//    @Autowired
//    private FileMapper fileMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public boolean upload(File file1) {
        int edit=userService.editImg(file1.getUrl());
//        int insert = fileMapper.insert(file1);

        return true;
    }

    @Override
    public String select(String fileName) {
        User user=userService.selectOne();
        if (user.getAvotor().substring(QiniuUtils.url.length()).equals(fileName)){
            return user.getAvotor();
        }
        return "";

    }
}
