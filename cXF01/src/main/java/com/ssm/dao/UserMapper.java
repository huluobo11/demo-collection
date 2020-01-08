package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.VO.User;
public interface UserMapper {
	/**
	 * 查找所有，不分页
	 * @return
	 */
	public List<User> list();
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public User getOneByPK(@Param(value = "id") Integer id);
	/**
	 * 分页的模糊查询
	 * @param name
	 * @return
	 */
	List<User> selectUserByName(@Param("name") String name);
	public int  addOneUser(User user);
	public void deleteOneUser(User user);
	/**
	 * 修改一个用户信息
	 * @param user
	 */
	public void updateOneUser(User user);
}
