package com.ssm.webService.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.ssm.VO.User;
@WebService
public interface IMyService {
	public User getUserById( long id);
	public List<User> getAllUsers();
}
