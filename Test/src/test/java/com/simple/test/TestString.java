package com.simple.test;

import java.io.IOException;

import org.junit.Test;

public class TestString {
	@Test
	public void ss() throws IOException {
		String final1 = "10101011";
		String final2 = "00001011";
		String f = final1 + final2;
		System.out.println(f);//1010101100001011
		Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler " + "http://www.baidu.com");
	}
}
