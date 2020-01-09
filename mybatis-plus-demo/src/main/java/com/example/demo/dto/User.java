package com.example.demo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class User {

    private int id;

    @JSONField(name = "name")
    private String username;

    private MultipartFile file;
}
