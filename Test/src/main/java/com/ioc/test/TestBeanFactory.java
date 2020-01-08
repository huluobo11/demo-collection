package com.ioc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @date 2017年10月31日上午9:21:43
 * @Description: TODO
 * @authorAdministrator
 */
public class TestBeanFactory {
	@Test
	public void testSimpleLoad() {
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("ApplicationContext.xml"));
		User user = xmlBeanFactory.getBean(User.class);
		assertEquals(10,user.getAge());
	}
}
