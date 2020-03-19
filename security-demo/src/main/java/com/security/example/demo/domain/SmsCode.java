package com.security.example.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SmsCode implements ValidateCode{
    private String code;
    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    @Override
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
    // get,set略
}
