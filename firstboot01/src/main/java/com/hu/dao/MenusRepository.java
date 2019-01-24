package com.hu.dao;

import com.hu.pojo.Menus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenusRepository extends CrudRepository<Menus, Integer> {
    //@Query 中的SQL用的是HQL，并不是一般的SQL语句。
    @Query("select bean from Menus bean where bean.parentId=?1 ")
    List<Menus> getFirst(int parentId);

    @Query("select bean from Menus bean where bean.parentId=?1 and SUBSTRING(bean.level,1,3)=?2 ")
    List<Menus> getSecond(int parentId, String parentIdString);

    @Query("select bean from Menus bean where bean.parentId=?1 and SUBSTRING(bean.level,1,6)=?2 ")
    List<Menus> getThird(int parentId, String level);


}
