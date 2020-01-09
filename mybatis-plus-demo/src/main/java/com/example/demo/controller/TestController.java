package com.example.demo.controller;

import com.example.demo.service.ITUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "测试类控制器",tags="用户类控制器")
@RestController
public class TestController {

    @Autowired
    private ITUserService userService;

    @ApiOperation(value = "获取用户列表",notes = "获取用户列表")
    @GetMapping("/test")
    public String test() {
        return userService.getString();
    }
}
