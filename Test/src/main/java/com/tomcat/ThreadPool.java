
package com.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tomcat.util.HandleRequestRunnable;
/**
 * demo3:线程池、
 * 为了避免持续创建新线程，可以通过使用简单的线程池来限定线程池的上限。
 * 线程池会管理所有线程，如果线程数还没有达到上限，线程池会创建线程到上限，且尽可能复用空闲的线程。
 * @author Administrator
 *
 */
public class ThreadPool {
	
public static void main(String[] args) throws IOException {
	ServerSocket listener = new ServerSocket(8080);
	ExecutorService executor = Executors.newFixedThreadPool(4);
	try {
	 while (true) {
	   Socket socket = listener.accept();
	   executor.submit( new HandleRequestRunnable(socket) );
	 }
	} finally {
	 listener.close();
	}
}
//这种方式确实是解决了多线程模式下创建新线程带来的资源消耗的问题，但是它还是有些问题。
/*在这个示例中，没有直接创建线程，而是使用了ExecutorService。它将需要执行的任务（需要实现Runnables接口）提交到线程池，
使用线程池中的线程执行代码。示例中，使用线程数量为4的固定大小线程池来处理所有请求。这限制了处理请求的线程数量，也限制了资源的使用。
除了通过newFixedThreadPool方法创建固定大小线程池，Executors类还提供了newCachedThreadPool方法。
复用线程池还是有可能导致不可控的线程数，但是它会尽可能使用之前已经创建的空闲线程。通常该类型线程池适合使用在不会被外部资源阻塞的短任务上。*/
}
