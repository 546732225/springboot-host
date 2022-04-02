package com.example.data.demo.controller;

import com.example.data.demo.domain.UserEntity;
import com.example.data.demo.query.UserQuery;
import com.example.data.demo.service.UserService;
import com.example.data.framework.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@Validated
@RestController
@Api(tags = "数据字典翻译")
public class TransformController {


    @Autowired
    private UserService userService;


    @GetMapping("list")
    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表", response = RestResponse.class)
    public RestResponse<List<UserEntity>> list(@Validated UserQuery query) {
        return RestResponse.success(userService.list(query));
    }


    @GetMapping("detail")
    @ApiOperation(value = "查询详情", notes = "查询详情")
    public RestResponse<Object> detail() throws InterruptedException {
        return RestResponse.success(userService.detail("1507239673481695233"));
    }

}
