package com.ds.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.springbootdemo.mapper.UserMapper;
import com.ds.springbootdemo.params.Result;
import com.ds.springbootdemo.params.UserVo;
import com.ds.springbootdemo.params.vo.PageParams;
import com.ds.springbootdemo.params.vo.UserParams;
import com.ds.springbootdemo.pojo.User;
import com.ds.springbootdemo.service.LoginService;
import com.ds.springbootdemo.service.UserService;
import com.ds.springbootdemo.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Rich_man
 * @title: UserServiceImpl
 * @date 2022/3/11  17:02
 * @description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String salt="ds@123";

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<Object,User> redisTemplate;

    @Override
    public Result getUsers(PageParams pageParams) {
        Page<User> page=new Page<>(
                pageParams.getPageNo(),
                pageParams.getPageSize());
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isBlank(pageParams.getUsername())){
            queryWrapper.like("username",pageParams.getUsername());
        }
        if (!StringUtils.isBlank(pageParams.getAddress())){
            queryWrapper.like("address",pageParams.getAddress());
        }
        if (!StringUtils.isBlank(pageParams.getEmail())){
            queryWrapper.like("email",pageParams.getEmail());
        }
        queryWrapper.orderByDesc("create_date");
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        List<User> userList = userPage.getRecords();
        List<UserVo> userListVo = copyList(userList);
        Map<Object,Object> map=new HashMap<>();
        map.put("userList",userListVo);
        map.put("total",userPage.getTotal());
        return  ReturnCheck.returnCheck(map);

    }

    @Override
    public Result addUser(User user) {
        /**
         * 分析：
         * 1.判断参数是否为合法
         * 2.合法后判断该用户是否存在
         * 存在：返回错误信息
         * 不存在：进行新用户的添加
         * 将token进行前端的传送
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User selectOne = userMapper.selectOne(queryWrapper);
        if (selectOne==null){
            user.setId(UUIDUtils.getUUID());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            userMapper.insert(user);
            String token = JWTUtils.createToken(user.getId());
            redisTemplate.opsForValue().set("token"+token, user,1, TimeUnit.DAYS);
            return Result.success(token);
        }
        return new Result(false,ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg(),user);
    }

    @Override
    public Result checkUser(UserParams user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",DigestUtils.md5Hex(user.getPassword()));
        User user1 = userMapper.selectOne(queryWrapper);
        if (user1!=null) {
            String token = JWTUtils.createToken(user1.getId());
            redisTemplate.opsForValue().set("token"+token,user1,1,TimeUnit.DAYS);
            return Result.success(token);
        }
        return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode());
    }

    /**
    *  @author: rich_man
    *  @Description:
     *  用户的更新
    */
    @Override
    public Result editUser(User user) {
        System.out.println(user);
        if (user.getPassword().length()>0){
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }
        int i = userMapper.updateById(user);
        return i>0?Result.success("成功！"):Result.fail("更新失败！",444);
    }

    @Override
    public Result delUsers(String[] ids) {
        List<String> list = Arrays.asList(ids);
        int i = userMapper.deleteBatchIds(list);
        return i>0 ? Result.success("批量删除成功"):Result.fail("批量删除失败！",444);
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Result importFile(List<User> users) {
        int count=0;
        for (User user:users) {
            Result result = this.addUser(user);
            if (result.isSuccess())count++;
        }
        return Result.success(count);
    }

    @Autowired
    LoginService loginSerivce;

    @Override
    public Result getUserInfo(String token) {
        /**
         * 1.判断token的合法性
         *  判断是否为空，判断解析是否成功，判断redis中是否存在
         * 2.校验失败，返回错误
         * 3.校验成功，返回数据
         */
        User user = loginSerivce.checkToken(token);
        if (user==null){
                return Result.fail( ErrorCode.TOKEN_ERROR.getMsg(),ErrorCode.TOKEN_ERROR.getCode());
        }
        System.out.println(user);
        return Result.success(user);
    }

    @Override
    public int editImg(String url) {

        User user=new User();
        user.setAvotor(url);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",UserThreadLocal.get().getId());
        int update = userMapper.update(user, queryWrapper);
        return update;
    }

    @Override
    public User selectOne() {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id", UserThreadLocal.get().getId());
        return userMapper.selectOne(queryWrapper);
    }


    /**
    *  @author: rich_man
    *  @Description:
     *  对象属性转换工具
    */

    public static List<UserVo> copyList(List<User> from ){
         List<UserVo> to=new ArrayList<>();
        for (User o:from) {
            UserVo obj=new UserVo();
            BeanUtils.copyProperties(o,obj);
            to.add(obj);
        }
        return to;
    }




}
