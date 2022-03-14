package com.ds.springbootdemo.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Rich_man
 * @title: UserVo
 * @date 2022/3/11  16:57
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserVo implements Serializable {
    private String id;
    private String username;
    private String nickname;
    private String email;
    private String address;

    private String phone;
}
