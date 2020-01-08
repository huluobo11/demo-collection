package com.test;

/**
 * @date 2017年9月20日上午11:51:30
 * @Description: TODO
 * @authorAdministrator
 */
public class TestInner {
	private int num=0;

	public void testInner(Inner in) {
		System.out.println(in);
		};

	public class Inner {
		void showName(){};         
		    
	}
	
	public static void main(String[] args) {
		TestInner i = new TestInner();
		i.testInner(i.new Inner());
	}
}
