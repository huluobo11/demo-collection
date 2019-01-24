package com.hu.service.test;

import com.hu.Springboot01Application;
import com.hu.service.impl.FirstService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//// 指定我们SpringBoot工程的Application启动类
@SpringBootTest(classes=Springboot01Application.class)
public class FirstServiceTest {
    @Autowired
    private FirstService firstService;
    @Test
    public void testGetName(){
        Assert.assertEquals("hello", firstService.getName());
    }
}
