package com.hu.dao;

import com.hu.pojo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //    JdbcTemplate用的是普通的SQL语是HQL.
    public void save(User user) {
        String sql = "insert into  it_user(username,age,birthday,role_id,gender) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getAge(), user.getBirthday(), user.getRoleId(), user.getGender()});
    }
}
