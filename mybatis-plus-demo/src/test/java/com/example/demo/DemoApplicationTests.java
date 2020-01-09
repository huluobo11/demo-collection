package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.TUser;
import com.example.demo.mapper.TUserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    TUserMapper userMapper;

    @Test
    public void getOne() {
        String dataStr = "2017-12-27 15:47:19";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dataStr, dateTimeFormatter);
        TUser tUser = userMapper.selectOne(new LambdaQueryWrapper<TUser>().eq(TUser::getCreateTime, localDateTime));
        Assert.assertNotNull(tUser);
        System.out.println(tUser);
    }

    @Test
    public void getList() {
        // 查询 userId > 10 的user 列表
        List<TUser> list = userMapper.selectList(Wrappers.lambdaQuery(new TUser()).gt(TUser::getUserId, 1).orderByAsc(TUser::getCreateTime).orderByDesc(TUser::getLastLoginTime));
        list.stream().forEach(System.out::println);
    }

}
