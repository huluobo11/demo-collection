package com.hu.service;

import com.hu.pojo.User;

public interface UserService {
    void save(User user);

    /**
     *查询一个userw
     * @param id
     * @return
     */
    User getOneById(long id);
    void deleteOneUser(long id);
}
