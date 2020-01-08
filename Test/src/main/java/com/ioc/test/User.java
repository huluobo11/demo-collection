package com.ioc.test;

/**
 * @date 2017年10月31日上午9:24:22
 * @Description: TODO
 * @authorAdministrator
 */
public class User {

	private int age = 10;
	private String name;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User() {
	}

	public User(int age, String name,String text) {
		super();
		this.age = age;
		this.name = name;
		this.text=text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
