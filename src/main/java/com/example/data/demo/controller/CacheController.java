package com.example.data.demo.controller;

import com.example.data.annotation.CacheLock;
import com.example.data.annotation.CacheParam;
import com.example.data.annotation.LocalLock;
import com.example.data.framework.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@Api(tags = "缓存测试")
public class CacheController {

    @PostMapping(value = "/localLock")
    @ApiOperation(value = "重复提交验证测试--使用本地缓存锁")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", dataType = "String")})
    @LocalLock(key = "localLock:test:arg[0]")
    public RestResponse<Void> localLock(String token) {
        return RestResponse.success();
    }



    @PostMapping(value = "/cacheLock1")
    @ApiOperation(value = "重复提交验证测试--使用redis锁")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "token", value = "token", dataType = "String")})
    @CacheLock(prefix = "redisLock.test", expire = 20)
    public String cacheLock1(String token) {
        return "success=====" + token;
    }

    @PostMapping(value = "/cacheLock2")
    @ApiOperation(value = "重复提交验证测试--使用redis锁")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "token", value = "token", dataType = "String")})
    @CacheLock(prefix = "redisLock.test", expire = 20)
    public String cacheLock2(@CacheParam(name = "token") String token) {
        return "success=====" + token;
    }

}
