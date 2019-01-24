package com.hu.service.impl;

import com.hu.dao.UserDao;
import com.hu.dao.UserRepository;
import com.hu.pojo.User;
import com.hu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;
    /*@Resource
    private RedisTemplate<String, Object> redisTemplate;*/

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    //用方法名+下划线+id值 作为key
    @Cacheable(value = "user", key = "#root.methodName+'_'+#id")
    @Override
    public User getOneById(long id) {
         String s = String.valueOf(3);
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    @Transactional
    @CacheEvict(value = "users", key = "#root.methodName+'_'+#id")
    @Override
    public void deleteOneUser(long id) {
        userRepository.deleteById(id);
    }

}
