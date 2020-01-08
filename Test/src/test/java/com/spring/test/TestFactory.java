
package com.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ss.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-testFactory.xml")
public class TestFactory {
	@Autowired
	User user;

	@Test
	public void testStaticFactory() {
		System.out.println(user);

	}
	@Test
	public void testFactory() {
		System.out.println(user);

	}
}
