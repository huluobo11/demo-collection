
package com.nio.test;
/**
 * 1.通道（Channel）：用于源节点 与目标节点的连接在java.nio中负责缓冲区中的数据的传输。Channel本身并不存储数据，因此需要配合缓冲区进行传输。
 * 2.通道的主要实现类
 * java.nio.channels.Channel接口:
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 * 3.如何获取通道
 * (1)java针对支持通道的类提供了getChannel()方法
 * 	本地IO:
 * 		FileInputStream/FileOutPutStream
 * 		RandomAccessFile
 * 网络IO:
 * 		Socket
 * 		ServerSocket
 *		DatagramSocket
 *(2)在jdk1.7中的NIO.2针对各个通道提供了一个静态方法 open();
 *(3)在jdk1.7中的NIO.2的Files工具类的newByteChannel();
 *4.通道之间的数据传输
 *transferFrom()
 *
 *transferTo();
 *5.分散(Scatter)与聚集(Gather)
 *分散读取(Scatter Reads)：将通道中的数据分散到多个缓冲区中
 *聚集写入(Gather Writes)：将多个缓冲区中的数据聚集到通道中
 *6.字符集Charset
 *编码：字符串-->字节数组
 *解码：字节数组-->字符串
 * @author Administrator
 *
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;

public class TestChannel {
//	字符集Charset
	
	@Test
	public void test6() throws CharacterCodingException {
		Charset GBK = Charset.forName("GBK");
//		获取编码器和解码器
		CharsetEncoder newEncoder = GBK.newEncoder();
		CharsetDecoder newDecoder = GBK.newDecoder();
		CharBuffer charBuffer = CharBuffer.allocate(1024);
		charBuffer.put("上去一个");
		charBuffer.flip();
		//编码
		ByteBuffer byteBuffer = newEncoder.encode(charBuffer);
		for(int i=0;i<8;i++) {
			System.out.println(byteBuffer.get());
		}
		byteBuffer.flip();
//		解码
		CharBuffer decode = newDecoder.decode(byteBuffer);
		System.out.println(decode.toString());
	}
	@Test
	public void test5() {
		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		Set<Entry<String, Charset>> entrySet = availableCharsets.entrySet();
		Iterator<Entry<String, Charset>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
		System.out.println(iterator.next().getKey()+"="+iterator.next().getValue());
		}
	}
	
	
	
	// 分散和聚集
	@Test
	public void test4() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
		// 1.获取通道
		FileChannel channel1 = raf1.getChannel();
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		ByteBuffer[] byteBuffers = new ByteBuffer[] { buffer1, buffer2 };
		channel1.read(byteBuffers);
		for (ByteBuffer byteBuffer : byteBuffers) {
			byteBuffer.flip();
		}
		channel1.close();
		raf1.close();
		System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
		System.out.println("--------------------------------------------------------");
		System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[1].limit()));
		RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(byteBuffers);
		channel2.close();
		raf2.close();
	}

	// 通道之间的数据传输（使用直接缓冲区）
	@Test
	public void testCopy3() throws IOException {
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("E:/1.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("E:/2.txt"), StandardOpenOption.READ,
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		// inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());
		inChannel.close();
		outChannel.close();
		long end = System.currentTimeMillis();
		System.out.println("时间：" + (end - start));
	}

	// 利用通道完成文件 的复制（使用直接缓冲区）
	@Test
	public void testCopy2() throws IOException {
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("E:/1.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("E:/2.txt"), StandardOpenOption.READ,
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		// 内存映射文件
		MappedByteBuffer inMappedBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		// 直接对缓冲 区进行数据的读写操作
		byte[] dest = new byte[inMappedBuffer.limit()];
		inMappedBuffer.get(dest);
		outMappedBuffer.put(dest);
		inChannel.close();
		outChannel.close();
		long end = System.currentTimeMillis();
		System.out.println("时间：" + (end - start));
	}

	// 利用通道完成文件 的复制（使用非直接缓冲区）
	@Test
	public void testCopy1() throws IOException {
		long start = System.currentTimeMillis();

		FileInputStream fis = new FileInputStream("E:/1.txt");
		FileOutputStream fos = new FileOutputStream("E:/2.txt");
		// 1.获取通道
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();

		// 2. 分配 指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 3.将通道中的数据存入缓冲区
		while (inChannel.read(buffer) != -1) {
			// 4.将缓冲区中的数据写入通道
			// 先将缓冲区切换成读取的状态
			buffer.flip();
			outChannel.write(buffer);
			// 5.清空缓冲区
			buffer.clear();
		}
		// 6.关闭流
		outChannel.close();
		inChannel.close();
		fis.close();
		fos.close();
		long end = System.currentTimeMillis();
		System.out.println("时间：" + (end - start));
	}
}
