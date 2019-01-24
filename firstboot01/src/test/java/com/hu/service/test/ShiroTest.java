package com.hu.service.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void ss() {
    	// 生成密码
        String algorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin11");
        int hashIterations = 2;
        SimpleHash simpleHash = new SimpleHash(algorithmName, credentials, salt, hashIterations);
        System.out.println(simpleHash);
    }
}
