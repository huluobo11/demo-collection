package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.VO.User;
import com.ssm.dao.UserMapper;
import com.ssm.service.UserService;
import com.ssm.util.DataSourceContextHolder;
import com.ssm.util.DynamicDataSourceAnnotation;
@Service
@DynamicDataSourceAnnotation
@Transactional
public class UserServiceImpl implements UserService{
	
private UserMapper userMapper;
@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public List<User> list() {
		return userMapper.list();
	}

	@Override
	public User getOneByPK(int id) {
		return userMapper.getOneByPK(id);
	}

	@Override
	public PageInfo<User> queryByPage(String name, Integer pageNo,
			Integer pageSize) {
		System.out.println("name====="+name);
		  pageNo = pageNo == null?1:pageNo;
		    pageSize = pageSize == null?3:pageSize;
		    
		    PageHelper.startPage(pageNo, pageSize);
		    
		    List<User> list = userMapper.selectUserByName(name);
		    System.out.println("size:"+list.size());
		    for (User user : list) {
				System.out.println(user.getName());
			}
		    //用PageInfo对结果进行包装
		    PageInfo<User> page = new PageInfo<User>(list);
		    //测试PageInfo全部属性
		    System.out.println(page.getPageNum());
		    System.out.println(page.getPageSize());
		    System.out.println(page.getStartRow());
		    System.out.println(page.getEndRow());
		    System.out.println(page.getTotal());
		    System.out.println(page.getPages());
		    System.out.println(page.getFirstPage());
		    System.out.println(page.getLastPage());
		    System.out.println(page.isHasPreviousPage());
		    System.out.println(page.isHasNextPage());
		    return page;
	}

	@Override
	@DynamicDataSourceAnnotation(dataSource="dataSourceB")
	public int addOneUser(User user)  {
		System.out.println("?是代理对象吗？"+userMapper.getClass().getName());
			int i=userMapper.addOneUser(user);
			//throw new RuntimeException("000");
			return i;
	}

	@Override
	public void deleteOneUser(User user) {
		userMapper.deleteOneUser(user);
		
	}

	@Override
	public void updateOneUser(User user) {
	 userMapper.updateOneUser(user);
		
	}
	
	 
	    public void changeDataSource() {
	        System.out.println("serviceImpl");
	    }
}
