package com.ds.springbootdemo.controller;

import com.ds.springbootdemo.pojo.File;
import com.ds.springbootdemo.service.FileService;
import com.ds.springbootdemo.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Rich_man
 * @title: UploadController
 * @date 2022/3/14  8:38
 * @description:
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Result file(@RequestParam("file")MultipartFile file){

        //原始文件名称,如index.png
        String originalFilename = file.getOriginalFilename();

        //避免文件名称的冲突，进行重命名（唯一的文件名称）
//        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");

        String fileName=DigestUtils.md5Hex(originalFilename)+ "." + StringUtils.substringAfterLast(originalFilename, ".");

        System.out.println(fileName);
        String url=fileService.select(fileName);
        if (url.length()!=0){
            return Result.success(url);
        }

        boolean upload = qiniuUtils.upload(file, fileName);

        if (upload){
            //操作数据库
            File file1=new File();
            file1.setName(originalFilename)
                            .setUrl(QiniuUtils.url+fileName)
                                    .setSize(file.getSize());
            boolean success=fileService.upload(file1);

            if (success)
            return Result.success(QiniuUtils.url+fileName);
        }

        return Result.fail("上传失败",0001);
    }

}
