
package com.nio.test;
/**
 * 非阻塞式nio
 * @author Administrator
 *
 */

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

import org.junit.Test;

public class TestNonBlokingNIO {
	// 客户端
	//@Test
	public void client() throws Exception {
		// 1.获取通道
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9896));
		// 2.切换成非阻塞模式
		socketChannel.configureBlocking(false);
		// 3.分配指定大小的缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		// 4.发送消息 到服务端
		byteBuffer.put(new Date().toString().getBytes());
		byteBuffer.flip();
		socketChannel.write(byteBuffer);
		byteBuffer.clear();
		socketChannel.close();
	}

	// 服务端
	//@Test
	public void server() throws Exception {
		// 1.获取通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 2.切换到非阻塞模式
		serverSocketChannel.configureBlocking(false);
		// 3.绑定端口号
		serverSocketChannel.bind(new InetSocketAddress(9896));
		// 4.获取选择器
		Selector selector = Selector.open();
		// 5.将通道注册到选择器上,并让选择器监听接收事件
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 6.轮询式的获取选择器上已经 “准备就绪”的事件
		while (selector.select() > 0) {
			// 7.获取当前选择器中所有注册的 “选择键”(已就绪的监听事件)
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				// 8.获取准备就绪的事件
				SelectionKey selectionKey = iterator.next();
				// 9.判断具体 是什么 事件 准备就绪 了
				if (selectionKey.isAcceptable()) {
					// 10.如果接收就绪，就获取客户端的连接
					SocketChannel socketChannel = serverSocketChannel.accept();
					// 11.切换成非阻塞模式
					socketChannel.configureBlocking(false);
					// 12.将该 通道注册到选择器上。
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (selectionKey.isReadable()) {
					// 13.获取当前 选择器上“读就绪”状态的通道。
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					// 14.读取 数据
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = socketChannel.read(byteBuffer)) > 0) {
						byteBuffer.flip();
						System.out.println(new String(byteBuffer.array(), 0, len));
						byteBuffer.clear();
					}
				}
				// 15.取消选择键
				iterator.remove();
			}

		}
	}
}
