
package com.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.tomcat.util.HandleRequestRunnable;

/**
 * demo2:多线程的tomcat 。
 * 收到一个请求就新开一个线程去处理任务，主线程返回，继续处理下一个任务，这种也是阻塞。
 * 相对第一个模型来说，它解决了主线程阻塞的问题，有了一定程度的并发量，但是在每个新开的线程中还是阻塞的。
 * 如果100个人同时访问，将会开100个线程，那1000个人，10000个人呢？频繁开关线程很消耗资源，这样实现的服务器性能依然不高。
 * 
 * @author Administrator
 *
 */
public class MultipartThread {
	/*
	 * 单线程的tomcat中,虽然handleRequest方法可能阻塞在IO上，但是CPU仍然可以处理更多的请求。
	 * 也就是说单线程并没有合理利用好CPU资源。因此，可以通过创建多线程的方式，来提升服务器的并行处理能力。
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(8080);
		/*
		 * 这里，accept()方法仍然在主线程中调用，但是一旦TCP连接建立之后，将会创建一个新的线程来处理新的请求，
		 * 既在新的线程中执行前文中的handleRequest方法。
		 * 通过创建新的线程，主线程可以继续接受新的TCP连接，且这些信求可以并行的处理。这个方式称为“每个请求一个线程（thread per request）”。
		 * 当然，还有其他方式来提高处理性能，例如NGINX和Node.js使用的异步事件驱动模型，但是它们不使用线程池，因此不在本文的讨论范围。
		 */
		try {
			while (true) {
				Socket socket = listener.accept();
				new Thread(new HandleRequestRunnable(socket)).start();
			}
		} finally {
			listener.close();
		}
		/*
		 * 在每个请求一个线程实现中，创建一个线程（和后续的销毁）开销是非常昂贵的，因为JVM和操作系统都需要分配资源。
		 * 另外，上面的实现还有一个问题，即创建的线程数是不可控的，这将可能导致系统资源被迅速耗尽。
		 */
/*		每个线程都需要一定的栈内存空间。在最近的64位JVM中，默认的栈大小是1024KB。如果服务器收到大量请求，或者handleRequest方法执行很慢，服务器可能因为创建了大量线程而崩溃。
		例如有1000个并行的请求，创建出来的1000个线程需要使用1GB的JVM内存作为线程栈空间。另外，每个线程代码执行过程中创建的对象，还可能会在堆上创建对象。
		这样的情况恶化下去，将会超出JVM堆内存，并产生大量的垃圾回收操作，最终引发内存溢出（OutOfMemoryErrors）。
		这些线程不仅仅会消耗内存，它们还会使用其他有限的资源，例如文件句柄、数据库连接等。不可控的创建线程，还可能引发其他类型的错误和崩溃。因此，避免资源耗尽的一个重要方式，就是避免不可控的数据结构。
		顺便说下，由于线程栈大小引发的内存问题，可以通过-Xss开关来调整栈大小。缩小线程栈大小之后，可以减少每个线程的开销，但是可能会引发栈溢出（StackOverflowErrors）。
		对于一般应用程序而言，默认的1024KB过于富裕，调小为256KB或者512KB可能更为合适。Java允许的最小值是160KB。*/
	}
}
