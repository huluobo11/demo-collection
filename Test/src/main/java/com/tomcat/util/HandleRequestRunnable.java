
package com.tomcat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import com.tomcat.SingletonThread;

public class HandleRequestRunnable implements Runnable {
	static Logger log = Logger.getLogger(SingletonThread.class);
	final static String response = "HTTP/1.0 200 OK\\r\\n" + "Content-type: text/plain\\r\\n" + "\\r\\n"
			+ "Hello World\\r\\n";
	 final Socket socket;
	 public HandleRequestRunnable(Socket socket) {
	   this.socket = socket;
	 }
	@Override
	public void run() {
		 try {
		     handleRequest(socket);
		   } catch (IOException e) {
		     e.printStackTrace();
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
