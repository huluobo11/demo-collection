
package com.tomcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

/**
 * demo1:单线程。
 * 模拟单线程阻塞模式的tomcat。 收到一个请求就处理，这个时候就不能处理新的请求， 这种为阻塞。
 * 这个是单线程模型，无法并发，一个请求没处理完服务器就会阻塞，不会处理下一个请求。一般的服务器不会使用这种方式实现。
 * 
 * @author Administrator
 *
 */
public class SingletonThread {
	static Logger log = Logger.getLogger(SingletonThread.class);
	final static String response = "HTTP/1.0 200 OK\\r\\n" + "Content-type: text/plain\\r\\n" + "\\r\\n"
			+ "Hello World\\r\\n";
	/*
	 * 创建了一个服务端套接字（ServerSocket），监听8080端口，然后循环检查这个套接字，查看是否有新的连接。
	 * 一旦有新的连接被接受，这个套接字会被传入handleRequest方法。这个方法会将数据流解析成HTTP请求，进行响应，并写入响应数据。
	 * 在这个简单的示例中，handleRequest方法仅仅实现数据流的读入，返回一个简单的响应数据。
	 * 在真正的应用中，handleRequest将会复杂的多。
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(8080);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					handleRequest(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			listener.close();
		}
	}

	public static void handleRequest(Socket socket) throws IOException {
		// Read the input stream, and return "200 OK"
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			log.info(in.readLine());
			OutputStream out = socket.getOutputStream();
			out.write(response.getBytes(StandardCharsets.UTF_8));
		} finally {
			socket.close();
		}
	}
}
