package com.hu.service;

import com.hu.pojo.Menus;

import java.util.List;

public interface MenusService {
    public void save(Menus menus);

    /**
     * 查询一级菜单
     *
     * @param parentId 0级菜单的id
     * @return
     */
    List<Menus> getFirst(int parentId);

    /**
     * 查询二级菜单
     *
     * @param parentId 一级菜单的id
     * @return
     */
    List<Menus> getSecond(int parentId);

    /**
     * 查询三级菜单
     *
     * @param parentId 二级菜单的id
     * @return
     */
    List<Menus> getThird(int parentId);
}
