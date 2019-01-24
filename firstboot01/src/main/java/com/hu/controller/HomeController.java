package com.hu.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@RequestMapping({ "/", "/index" })
	public String index() {
		System.out.println("-----------------index-------------------------");
		return "/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, 
			Map<String, Object> map)	throws Exception {
		System.out.println("HomeController.login()");
		Subject currentUser = SecurityUtils.getSubject();
		String msg = "";
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			try {
				System.out.println("1.hash:" + token.hashCode());
				currentUser.login(token);
			} catch (UnknownAccountException e) {
				System.out.println("----------登录失败：" + e.getMessage());
				System.out.println("UnknownAccountException -- > 账号不存在：");
				msg = "UnknownAccountException -- > 账号不存在：";
			} catch (IncorrectCredentialsException e) {
				System.out.println("----------登录失败：" + e.getMessage());
				System.out.println("IncorrectCredentialsException -- > 密码不正确：");
				msg = "IncorrectCredentialsException -- > 密码不正确：";
			} catch (AuthenticationException e) {
				System.out.println("----------登录失败：" + e.getMessage());
			}
		}
		map.put("msg", msg);
		// 此方法不处理登录成功,由shiro进行处理.
		return "/login";
	}
}
