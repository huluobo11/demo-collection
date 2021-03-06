package com.socket;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @date 2017年9月6日
 * @author Administrator
 * @project Test
 */
public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		Socket client = serverSocket.accept();
		OutputStream outputStream = client.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.write("My First Socket  !");
		bufferedWriter.flush();
		outputStream.close();
		bufferedWriter.close();
		client.close();
	}
}
