
package com.simple.test;

import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

import com.ss.pojo.User;
import com.util.JsonUtils;

public class testJson {
	public static void main(String[] args) {
		/*
		 * ObjectMapper mapper = new ObjectMapper(); //转换器
		 * 
		 * //测试01：对象--json String json; try { json = mapper.writeValueAsString(user);
		 * System.out.println(json); } catch (JsonProcessingException e) {
		 * e.printStackTrace(); }
		 */
		// Calendar

		Scanner sc = new Scanner(System.in);
		sc.close();
	}

	@Test
	public void testJacksonDate() {
		User user = new User();
		user.setBirthday(new Date());
		user.setId(1);
		user.setName("SW");
		String objectToJson = JsonUtils.objectToJson(user);
		System.out.println(objectToJson);
	}
}
