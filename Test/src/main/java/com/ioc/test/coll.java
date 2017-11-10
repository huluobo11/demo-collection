package com.ioc.test;

import java.util.Arrays;
import java.util.List;

public class coll {
	private String username;
	private List listData;
	private String[] strArray;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getStrArray() {
		return strArray;
	}

	public void setStrArray(String[] strArray) {
		this.strArray = strArray;
	}

	public List getListData() {
		return listData;
	}

	public void setListData(List listData) {
		this.listData = listData;
	}

	@Override
	public String toString() {
		return "coll [username=" + username + ", listData=" + listData + ", strArray=" + Arrays.toString(strArray)
		+ "]";
	}

	
}
