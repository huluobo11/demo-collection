
package com.nio.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestPipe {
	@Test
	public void test1() throws IOException {
		// 1.获取管道
		Pipe pipe = Pipe.open();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		SinkChannel sinkChannel = pipe.sink();
		byteBuffer.put("通过单向管道发送数据".getBytes());
		byteBuffer.flip();
		// 2.将缓冲区中的数据写入管道
		sinkChannel.write(byteBuffer);
		
		
		
		
		// 3.读取缓冲区中的数据
		SourceChannel sourceChannel = pipe.source();
		byteBuffer.flip();
		sourceChannel.read(byteBuffer);
		System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));

		sourceChannel.close();
		sinkChannel.close();

	}
}
