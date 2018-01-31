
package com.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestBlokingNIO2 {
//客户端
	//@Test
	public void client() throws IOException {
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		FileChannel fileChannel=FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
		ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
		while(fileChannel.read(byteBuffer)!=-1) {
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
		}
		socketChannel.shutdownOutput();
		//接收服务器端的反馈
		int len=0;
		while((len=socketChannel.read(byteBuffer))!=-1) {
			byteBuffer.flip();
			System.out.print(new String(byteBuffer.array(),0,len));
			byteBuffer.clear();
		}
		fileChannel.close();
		socketChannel.close();
		
	}
	//服务端
		//@Test
		public void server() throws IOException {
			ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(9898));
			FileChannel fileChannel=FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			SocketChannel socketChannel = serverSocketChannel.accept();
			ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
			while(socketChannel.read(byteBuffer)!=-1) {
				byteBuffer.flip();
				fileChannel.write(byteBuffer);
				byteBuffer.clear();
			}
			byteBuffer.put("服务器接收数据成功：".getBytes());
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
			socketChannel.close();
			serverSocketChannel.close();
			fileChannel.close();
		}
}
