package com.ds.springbootdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Rich_man
 * @title: File
 * @date 2022/3/14  9:00
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class File {

     @TableId(type = IdType.AUTO)
     private Integer id;
     private String name;
     private Long size;
     private String url;

     private Integer isDelete;
     private Integer enable;
}
