package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

/**
 * @date 2017年9月6日
 * @author Administrator
 * @project Test
 */
<<<<<<< HEAD
public class TestUrl {        
=======
public class TestUrl {
>>>>>>> branch 'master' of https://github.com/huluobo11/cxftest.git
	@Test
	public void test() throws URISyntaxException, IOException{
		 URL url = new URL("http://www.baidu.com");
		 URLConnection urlConnection = url.openConnection();
		 InputStream inputStream = urlConnection.getInputStream();
		 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		 String line=null;
		 StringBuffer sb=new StringBuffer();
		 while((line= bufferedReader.readLine())!=null){
			 sb.append(line);
			 sb.append("\n\t");
		 }
		System.out.println(sb);
	}
}
