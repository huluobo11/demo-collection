package com.hu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;
@Controller
public class TemplateController {
    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String, Object> map) {
        map.put("hello", "from TemplateController.helloHtml");
        return "/helloHtml";
    }
    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("hello","from TemplateController.helloFtl");
        return"/helloFtl";
    }
    @RequestMapping("/testShutdown")
    public String shutdown(Map<String,Object> map) throws IOException {
       // Runtime.getRuntime().exec("rundll32.exe user.exe,exitwindows");
        return"/helloFtl";
    }

}
