package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;

/**
 * @author small-white
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class TUserController  {

    @RequestMapping(value = "get1", method = RequestMethod.GET)
    public String get1(String id) {
        return "user/get1/test------------success=" + id;
    }
}
