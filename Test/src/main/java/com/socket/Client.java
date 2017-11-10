package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @date 2017年9月6日
 * @author Administrator
 * @project Test
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost", 9999);
		InputStream inputStream = client.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String readLine = bufferedReader.readLine();
		System.out.println("客户端收到："+readLine);
		bufferedReader.close();
		client.close();
	}
}
