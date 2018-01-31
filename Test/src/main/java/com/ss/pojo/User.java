package com.ss.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Comparable<User>,Serializable {
	private static final long serialVersionUID = -4139366487106370736L;
	private int id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(User user2) {
		if (user2 == null) {

			return -1;
		}
		if (user2.getId() == id && ((name == null && user2.getName() == null) || name.equals(user2.getName()))) {
			return 0;
		}
		return -1;
	}

}
