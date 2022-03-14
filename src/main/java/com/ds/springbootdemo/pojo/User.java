package com.ds.springbootdemo.pojo;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Rich_man
 * @title: User
 * @date 2022/3/11  15:38
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    @TableId
    private String id;
    @Alias("用户名")
    private String username;

    @Alias("密码")
    private String password;
    @Alias("昵称")
    private String nickname;
    @Alias("邮箱")
    private String email;
    @Alias("地址")
    private String address;
    @Alias("手机号")
    private String phone;
    @Alias("创建时间")
    private String createDate;
    @Alias("头像地址")
    private String avotor;

}
