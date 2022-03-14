package com.ds.springbootdemo.service;

import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.params.vo.PageParams;
import com.ds.springbootdemo.params.vo.UserParams;
import com.ds.springbootdemo.pojo.User;

import java.util.List;

/**
 * @author Rich_man
 * @title: UserService
 * @date 2022/3/11  17:02
 * @description:
 */
public interface UserService {
    /**
    *  @author: rich_man
    *  @Description:
     *  通过分页插件进行分页查询
    */
    Result getUsers(PageParams page);

    /**
    *  @author: rich_man
    *  @Description:
     *  进行用户的添加
    */
    Result addUser(User user);

    /**
    *  @author: rich_man
    *  @Description:
     *  通过id获取用户相关信息
    */
    Result checkUser(UserParams user);

    /**
    *  @author: rich_man
    *  @Description:
     *  进行用户的更新
    */
    Result editUser(User user);

    /**
    *  @author: rich_man
    *  @Description:
     *  批量删除用户
    */
    Result delUsers(String[] ids);

    /**
    *  @author: rich_man
    *  @Description:
     *  查询用户所有的信息
    */
    List<User> list();

    /**
    *  @author: rich_man
    *  @Description:
     *  通过文件导入添加用户
    */
    Result importFile(List<User> users);

    /**
    *  @author: rich_man
    *  @Description:
     *  通过id获取用户信息
    */
    Result getUserInfo(String token);

    /**
    *  @author: rich_man
    *  @Description:
     *  向数据路中添加头像信息
    */
    int editImg(String url);

    User selectOne();
}
