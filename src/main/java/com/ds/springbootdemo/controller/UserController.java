package com.ds.springbootdemo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.params.vo.PageParams;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Rich_man
 * @title: UserController
 * @date 2022/3/11  17:02
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
    *  @author: rich_man
    *  @Description:
     *  分页查询用户
    */
    @PostMapping("")
    public Result getUsers(@RequestBody PageParams page){
        return userService.getUsers(page);
    }

    /**
    *  @author: rich_man
    *  @Description:
     *  进行用户的添加
    */

    @PostMapping("add")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    @PostMapping("edit")
    public Result editUser(@RequestBody User  user){
        return userService.editUser(user);
    }

    @PostMapping("del")
    public Result delUsers(@RequestBody String[] ids){
        return userService.delUsers(ids);
    }


    @GetMapping("getInfo/{id}")
    public Result getUserById(@PathVariable("id")String id){
        return userService.getUserInfo(id);
    }

    @GetMapping("currentUser")
    public Result getUserInfo(@RequestHeader("token") String token ){
        return userService.getUserInfo(token);
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("username", "用户名");
//        writer.addHeaderAlias("password", "密码");
//        writer.addHeaderAlias("nickname", "昵称");
//        writer.addHeaderAlias("email", "邮箱");
//        writer.addHeaderAlias("phone", "电话");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("createDate", "创建时间");
//        writer.addHeaderAlias("avatarUrl", "头像");
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        ////test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }


    /**
    *  @author: rich_man
    *  @Description:
     *  导入接口
    */
    @PostMapping("import")
    public Result importFile(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(in);
        List<User> users = reader.readAll(User.class);
        return userService.importFile(users);
    }
}
