package com.ssm.webService.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ssm.VO.User;
import com.ssm.webService.service.IMyService;
public class MyService implements IMyService {

	@Override
	public User getUserById(long id) {
		User user = new User(100000, "testhao");
		System.out.println(user.toString());
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		User user = new User(100000, "test");
		User user1 = new User(100001, "test1");
		User user2 = new User(100002, "test2");
		list.add(user);
		list.add(user1);
		list.add(user2);
		System.out.println(list.toString());
		return list;
	}

}
