package com.example.data.demo.controller;

import com.example.data.annotation.LocalLock;
import com.example.data.demo.domain.entity.UserEntity;
import com.example.data.demo.domain.query.UserQuery;
import com.example.data.demo.service.UserService;
import com.example.data.framework.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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




    @GetMapping("page")
    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表", response = RestResponse.class)
    public RestResponse<PageInfo<UserEntity>> page(@Validated UserQuery query) {
        return RestResponse.success(PageHelper.startPage(1, 10).doSelectPageInfo(() -> userService.list(query)));
    }



    @GetMapping("user2")
    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表", response = RestResponse.class)
    public RestResponse<List<UserEntity>> user2(@Validated UserQuery query) {
        return RestResponse.success(userService.list2(query));
    }




    @GetMapping("user")
    @LocalLock(key = "localLock:user:arg[0]")
    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表", response = RestResponse.class)
    public RestResponse<List<UserEntity>> list(@Validated UserQuery query) {
        return RestResponse.success(userService.list(query));
    }


    @PostMapping("user")
    @LocalLock(key = "localLock:insert:user:arg[0]")
    @ApiOperation(value = "新增用户", notes = "新增用户信息", response = RestResponse.class)
    public RestResponse<String> insert(@RequestBody UserEntity entity) {
        userService.insert(entity);
        return RestResponse.success();
    }


    @PutMapping("user")
    @ApiOperation(value = "修改用户")
    public RestResponse<String> update(@RequestBody UserEntity entity) {
        userService.update(entity);
        return RestResponse.success();
    }

    @PatchMapping("user")
    @ApiOperation(value = "修改用户")
    public RestResponse<String> patch(@RequestBody UserEntity entity) {
        userService.update(entity);
        return RestResponse.success();
    }

    @GetMapping("user/{id}")
    @ApiOperation(value = "查询用户详情")
    public RestResponse<UserEntity> detail(@PathVariable("id") String id){
        return RestResponse.success(userService.detail(id));
    }


    @DeleteMapping("user/{id}")
    @ApiOperation(value = "删除用户")
    public RestResponse<String> delete(@PathVariable("id") String id) {
        userService.delete(id);
        return RestResponse.success();
    }





}
