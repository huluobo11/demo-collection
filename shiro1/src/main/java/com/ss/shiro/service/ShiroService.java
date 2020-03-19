package com.ss.shiro.service;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {
	// 如果service层中用的是@Transactional注解，那么shiro的权限控制的注解(@RequiresUser.@RequiresRoles等)需要放到controller层中
	// ，如果强行放到service层中，则会抛出类型转换异常。

	@RequiresRoles({ "admin" })
	public void testMethod() {
		System.out.println("testMethod :" + new Date());
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		System.out.println(session.getAttribute("sessionKey"));
	}
}
