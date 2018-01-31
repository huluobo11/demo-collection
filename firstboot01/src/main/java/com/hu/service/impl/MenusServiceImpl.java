package com.hu.service.impl;

import com.hu.dao.MenusRepository;
import com.hu.pojo.Menus;
import com.hu.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenusServiceImpl implements MenusService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MenusRepository menusRepository;

    @Override
    public void save(Menus menus) {
        menusRepository.save(menus);
    }

    @Override
    public List<Menus> getFirst(int parentId) {

        return menusRepository.getFirst(parentId);
    }

    @Override
    public List<Menus> getSecond(int parentId) {

        return menusRepository.getSecond(parentId,parentId+"");
    }

    @Override
    public List<Menus> getThird(int parentId) {
        Menus menus = menusRepository.findOne(parentId);
        int parentId1 = menus.getParentId();
        String level = parentId1 + "" + parentId;
        return menusRepository.getThird(parentId, level);
    }


}
