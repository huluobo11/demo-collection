
package com.ss.factory;

import com.ss.pojo.User;

public class UserFactory {
	public User getInstance2() {
		return new User();
	}
	
	
	public static User getInstance() {
		return new User();
	}

	
}
