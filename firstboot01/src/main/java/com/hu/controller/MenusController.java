package com.hu.controller;

import com.hu.pojo.Menus;
import com.hu.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenusController {
    @Autowired
    private MenusService menusService;

    @RequestMapping("/save")
    public Menus save() {
        Menus m = new Menus();
        m.setId(102);
        m.setLevel("102");
        m.setName("一级菜单2");
        m.setParentId(0);
        menusService.save(m);
        return m;
    }

    @RequestMapping("/first")
    public List<Menus> getFirst() {
        return menusService.getFirst(0);
    }

    @RequestMapping("/second")
    public List<Menus> getSecond() {
        return menusService.getSecond(101);
    }

    @RequestMapping("/third")
    public List<Menus> getThird() {

        return menusService.getThird(103);
    }
}
