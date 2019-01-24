package com.hu.controller;

import com.alibaba.fastjson.JSONObject;
import com.hu.pojo.User;
import com.hu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "first Login";
    }


    @RequestMapping("/test")
    public User test() {
        User user = new User();
        user.setBirthday(new Date());
        user.setId(1L);
        user.setUsername("三");
        System.out.println("11111111111");
        return user;
    }

    @RequestMapping("/testJson")
    public String testJson() {
        // int x=10/0;
        User user=userService.getOneById(1L);
        return JSONObject.toJSONString(user);
    }

    @RequestMapping("/insert")
    public User insert() {
        User user = new User();
        user.setBirthday(new Date());
        user.setId(1L);
        user.setUsername("三");
        user.setAge(13);
        user.setGender("1");
        user.setRoleId(1);
        userService.save(user);
        return  user;
    }
    @RequestMapping("/getOne")
    public User getOne(){
        User user = userService.getOneById(1L);
        return user;
    }
    @RequestMapping("/deleteOne")
    public String deleteOne(){
         userService.deleteOneUser(1L);
        return "666";
    }
}
