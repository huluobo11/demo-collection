package com.test;

import java.io.File;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ioc.test.coll;

/**
 * @date 2017年8月25日
 * @author Administrator
 * @project Test
 */
public class testIOC {
private File file;
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/ApplicationContext.xml");
		coll bean = ctx.getBean(coll.class);
		System.out.println(bean.getUsername());
		System.out.println(bean.getListData());
		System.out.println(bean.getStrArray().length);
	}

}
