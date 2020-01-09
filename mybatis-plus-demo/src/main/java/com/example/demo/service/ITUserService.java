package com.example.demo.service;

import com.example.demo.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author small-white
 */
public interface ITUserService extends IService<TUser> {
    String getString();
}
