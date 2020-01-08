package com.hu.dao;

import com.hu.pojo.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long>{
    /**通过username查找用户信息;*/
     UserInfo findByUsername(String username);
}
