package com.simple.test;

import org.junit.Test;

public class TestConstant {

	public TestConstant() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test2() {
		/*
		 * A:二进制 由0，1组成。以0b开头。 B:八进制 由0，1，...7组成。以0开头。 C:十进制 由0，1，...9组成。整数默认是十进制。
		 * D:十六进制 由0，1，...9,a,b,c,d,e,f(大小写均可)组成。以0x开头。
		 */
		System.out.println(0b100);
		System.out.println(0100);
		System.out.println(100);
		System.out.println(0X100);
		System.out.println(1 ^ 3);// 001 和 011运算
		System.out.println((1 < 2) ^ (2 < 3));//^:相同则false，不同则true。
	}
}
