package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.VO.User;



public class testJson {
public static void main(String[] args) {
	 ObjectMapper mapper = new ObjectMapper(); //转换器  
     User user=new User();
     user.setId(1001);
     user.setName("789");
     String json;
		try {
			json = mapper.writeValueAsString(user);
			System.out.println(json);  
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}   
}
}
