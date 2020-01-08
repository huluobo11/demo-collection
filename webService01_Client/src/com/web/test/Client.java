package com.web.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Client {
	public static String sendPost(String url, String param) {
	PrintWriter out = null;
	BufferedReader in = null;
	String result = "";
	try {
		URL realUrl = new URL(url);
		// 打开和URL之间的连接
		URLConnection conn = realUrl.openConnection();
		// 设置通用的请求属性
		/*conn.setRequestProperty("accept", "*//*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");*/
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// 获取URLConnection对象对应的输出流
		out = new PrintWriter(conn.getOutputStream());
		// 发送请求参数
		out.print(param);
		// flush输出流的缓冲
		out.flush();
		// 定义BufferedReader输入流来读取URL的响应
		in = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += "/n" + line;
		}
	} catch (Exception e) {
		System.out.println("发送POST请求出现异常！" + e);
		e.printStackTrace();
	}
	// 使用finally块来关闭输出流、输入流
	finally {
		try {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	return result;
}

// 提供主方法，测试发送GET请求和POST请求
public static void main(String args[]) {
	System.out.println("______________________");
	// 发送GET请求
	String s = Client.sendPost("http://localhost:8888/webService01/services/service?num1=22&num2=33",
			"a=99");
	System.out.println("______result_____________"+s);
	// 发送POST请求
	
}
}
