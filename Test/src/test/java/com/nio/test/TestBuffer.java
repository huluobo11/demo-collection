
package com.nio.test;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 1.缓冲区在nio中负责数据的存储， 缓冲区也就是数组。 每个数据类型都有对应的缓冲区类型（boolean除外），
 * 2.上述缓冲区的管理一样，都通过allocate()获取缓冲区。
 * 3.缓冲区存取数据的两个核心方法是put()存入数据到缓冲区、get()获取缓冲区中的数据 4.缓冲区存取数据的四个核心属性。
 * capacity:容量，表示缓冲区中最大存储的数据的容量，一旦声，不能改变。
 * limit:界线，表示缓冲区中可以操作的数据的大小（limit后的数据不能进行读写。） position:位置，表示缓冲区中正在操作的数据的位置
 * 0<=mark<=position<=limit<=capacity
 * mark:标记，表示记录当前position的位置。可能通过reset()恢复到mark的位置 。 4.直接缓冲区和非直接缓冲区，
 * 非直接缓冲区：通过allocate()方法分配缓冲区， 将缓冲区建立在JVM的内存中。
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在操作系统的物理内存中。
 * 
 * @author Administrator
 *
 */
public class TestBuffer {
	@Test
	public void test3() {
		// 直接缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
		// 打印是否是直接缓冲 区。
		System.out.println(byteBuffer.isDirect());
	}

	@Test
	public void test2() {
		String ss = "asdef";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(ss.getBytes());
		buffer.flip();
		byte[] dest = new byte[buffer.limit()];
		buffer.get(dest, 0, 2);
		System.out.println(new String(dest));
		System.out.println(buffer.position());
		// 用mark标记一下
		buffer.mark();
		buffer.get(dest, 2, 2);
		System.out.println(new String(dest));
		System.out.println(buffer.position());
		buffer.reset();// reset后，position
		System.out.println(buffer.position());
		if (buffer.hasRemaining()) {
			// 如果缓冲区中还有数据，看一下可以操作的数据有多少 。
			System.out.println(buffer.remaining());
		}
	}

	@Test
	public void test1() {
		// 1.分配一个指定大小的缓冲区。
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		// 2.用put方法存入数据到缓冲区
		buffer.put("abcde".getBytes());
		System.out.println("------------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		// 3.用flip()方法切换到读取数据模式。
		buffer.flip();
		System.out.println("------------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		// 4.用get()读取数据。
		byte[] bytes = new byte[buffer.limit()];
		buffer.get(bytes);
		System.out.println("------------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		System.out.println(new String(bytes));
		// 5.rewind()可以重复读取。
		buffer.rewind();
		System.out.println("------------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		// 6.clear()清空缓冲区。但是缓冲区中的数据依然存在，只是数据处于“被遗忘状态”。
		buffer.clear();
		System.out.println("------------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
	}
}
