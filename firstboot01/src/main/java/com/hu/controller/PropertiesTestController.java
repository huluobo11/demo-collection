package com.hu.controller;

import com.hu.configuration.Wisely2Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesTestController {
    @Autowired
    private Wisely2Settings wisely2Settings;

    @RequestMapping("/testProperties")
    public String testProperties() {
        System.out.println("------");
        System.out.println(wisely2Settings.getGender());
        return wisely2Settings.getName();
    }
}
