package com.example.data.demo.controller;

import com.example.data.demo.domain.UserEntity;
import com.example.data.demo.query.UserQuery;
import com.example.data.demo.service.UserService;
import com.example.data.framework.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@Api(tags = "用户管理")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

//    @GetMapping("user")
//    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表", response = RestResponse.class)
//    public RestResponse<List<UserEntity>> list(@Validated UserQuery query) {
//        return RestResponse.success(userService.list(query));
//    }


    @GetMapping("user")
    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表", response = RestResponse.class)
    public RestResponse<List<UserEntity>> list(@Validated UserQuery query) {
        return RestResponse.success(userService.list(query));
    }


    @PostMapping("user")
    @ApiOperation(value = "新增用户", notes = "新增用户信息", response = RestResponse.class)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ennotestity", value = "用户实体信息", required = true),
    })
    public RestResponse<Void> insert(@RequestBody UserEntity entity) {
//        return RestResponse.restForJson((RestProcess) () -> userService.insert(entity));
        return RestResponse.success();
    }


    @PutMapping("user")
    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(name = "entity", value = "用户实体信息", dataTypeClass = UserEntity.class)
    public RestResponse<Void> update(@RequestBody UserEntity entity) {
        userService.update(entity);
        return RestResponse.success();
    }

    @PatchMapping("user")
    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(name = "entity", value = "用户实体信息")
    public RestResponse<Void> patch(@RequestBody UserEntity entity) {
        userService.update(entity);
        return RestResponse.success();
    }

    @GetMapping("user/{id}")
    @ApiOperation(value = "查询用户详情")
    public RestResponse<UserEntity> detail(@PathVariable("id") String id) throws InterruptedException {
        Thread.sleep(1000);
        return RestResponse.success(userService.detail(id));
    }


    @DeleteMapping("user/{id}")
    @ApiOperation(value = "删除用户")
    public RestResponse<Void> delete(@PathVariable("id") String id) {
        userService.delete(id);
        return RestResponse.success();
    }

}