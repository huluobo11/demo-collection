
package com.spring.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.spring.pojo.Coll;
import com.spring.pojo.User;

/**
 * @date 2017年10月31日上午9:21:43
 * @Description: TODO
 * @authorAdministrator
 */
public class TestBean {
	@Test
	public void testSimpleLoad() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
		Coll bean =  ctx.getBean(Coll.class);
		System.out.println(bean.getUsername());
		System.out.println(bean.getListData());
		//System.out.println(bean.getStrArray().length);
	}

	@Test
	public void testLoad() {
		ClassPathResource classPathResource = new ClassPathResource("spring/ApplicationContext.xml");
		@SuppressWarnings("deprecation")
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(classPathResource);
		User user = xmlBeanFactory.getBean(User.class);
		assertEquals(10, user.getAge());
		@SuppressWarnings("resource")
		ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
		ApplicationContext.getBean(User.class);
	}

	@Test
	public void testB() {
		char b = 'B';
		char x=(char) (b+3);
		System.out.println(x);
	}
}
