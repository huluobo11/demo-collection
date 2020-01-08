package com.ioc.test;

import org.junit.Test;

/**
 * @date 2017年10月20日下午2:55:35
 * @Description: 
 * @authorAdministrator
 */
public class TestClass {
@Test
public void get(){
	
	String string = this.getClass().getClassLoader().getResource("").toString();
	System.out.println(string);
}
}
