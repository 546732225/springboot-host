package com.example.data.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.annotation.CheckSqlInjection;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_user")
public class UserEntity implements Serializable {

    @TableId
    private String id;

    @CheckSqlInjection
    private String name;

    @CheckSqlInjection
    private Integer age;

    @CheckSqlInjection
    private String username;

    @CheckSqlInjection
    private String password;

    @CheckSqlInjection
    private String address;


    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

}
