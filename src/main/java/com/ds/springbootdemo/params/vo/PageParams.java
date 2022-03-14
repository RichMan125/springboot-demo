package com.ds.springbootdemo.params.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rich_man
 * @title: pageParams
 * @date 2022/3/11  16:35
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    private int pageSize;
    private int pageNo;
    private String username;
    private String email;
    private String address;
}
