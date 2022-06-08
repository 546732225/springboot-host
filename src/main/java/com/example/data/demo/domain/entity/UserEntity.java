package com.example.data.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.annotation.FieldParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_user")
public class UserEntity implements Serializable {

    public UserEntity() {
    }

    @TableId
    private String id;

    private String name;

    private Integer age;

    private String username;

    private String password;

    private String address;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;


    private String status;


    @FieldParam
    private String gender;


}
