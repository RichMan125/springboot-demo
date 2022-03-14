package com.ds.springbootdemo.service;

import com.ds.springbootdemo.pojo.File;

/**
 * @author Rich_man
 * @title: FileService
 * @date 2022/3/14  9:07
 * @description:
 */
public interface FileService {

    /**
    *  @author: rich_man
    *  @Description:
     *  存储文件信息
    */
    boolean upload(File file1);

    /**
    *  @author: rich_man
    *  @Description:
     *  是否曾经是否存储过相同的文件
    */
    String select(String fileName);
}
