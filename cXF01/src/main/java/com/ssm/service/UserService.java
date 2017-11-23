package com.ssm.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ssm.VO.User;

public interface UserService {

	List<User> list();

	User getOneByPK(int id);

	PageInfo<User> queryByPage(String userName,Integer pageNo,Integer pageSize);

	int addOneUser(User user) throws Exception;

	void deleteOneUser(User user);

	/**
	 *修改一个用户信息
	 * @param user 要修改的用户
	 */
	void updateOneUser(User user);
}
