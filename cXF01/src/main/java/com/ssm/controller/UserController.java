package com.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ssm.VO.User;
import com.ssm.service.UserService;
@Controller
public class UserController {
@Autowired
private UserService userService;
	
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Map<String,Object> list() {
		HashMap<String, Object> hashMap = new HashMap<>();
		List<User> userList=userService.list();
		hashMap.put("userList", userList);
		return hashMap;
	}
	
	
	@RequestMapping("/getOneById")
	@ResponseBody
	public  User getOneById(@RequestBody User user){
		System.out.println("---------"+user);
		 user=userService.getOneByPK(user.getId());
		return user;
	}
	
	@RequestMapping("/queryUserByNameForPage")
	@ResponseBody
	public  Map<String,Object> queryUserByNameForPage(@RequestBody User user){
		System.out.println("++++"+user.getName());
		Map<String,Object> map=new HashMap<>();
		PageInfo<User> queryByPage = userService.queryByPage(user.getName(), null, null);
		map.put("pageList", queryByPage.getList());
		map.put("pageCount", queryByPage.getTotal());
		map.put("pageCurrent", queryByPage.getPageNum());
		map.put("pageSize", queryByPage.getPageSize());
		return map;
	}
	
	@RequestMapping(value="/addOneUser",method = { RequestMethod.POST})
	@ResponseBody
	public   Map<String,Object> addOneUser(@RequestBody User user) throws Exception{
		Map<String,Object> map=new HashMap<>();
		//System.out.println("?是代理对象吗？"+userService.getClass().getName());
		userService.addOneUser(user);
		System.out.println("PK="+user.getId());
		map.put("code", "8");
		return map;
	}
	@RequestMapping("/deleteOneUser")
	public String deleteOneUser(User user){
		userService.deleteOneUser(user);
		return "/pages/list.jsp";
	}
	
	@RequestMapping("/getOneUser")
	public ModelAndView getOneUser(User user){
		ModelAndView mv=new ModelAndView();
		user=userService.getOneByPK(user.getId());
		mv.addObject("user", user);
		mv.setViewName("/pages/user_upd.jsp");
		return mv;
	}
	@RequestMapping("/updateOneUser")
	@ResponseBody
	public Map<String,Object> updateOneUser(@RequestBody User user){
		Map<String,Object> map=new HashMap<>();
		userService.updateOneUser(user);
		map.put("code", "8");
		return map;
	}
}
