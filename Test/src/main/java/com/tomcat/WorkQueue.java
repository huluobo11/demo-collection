
package com.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import com.tomcat.util.HandleRequestRunnable;

/**
 * demo4:工作队列。
 * 使用了固定大小线程池之后，如果所有的线程都繁忙，再新来一个请求将会发生什么呢？ThreadPoolExecutor使用一个队列来保存等待处理的请求，固定大小线程池默认使用无限制的链表。
 * 注意，这又可能引起资源耗尽问题，但只要线程处理的速度大于队列增长的速度就不会发生。然后前面示例中，每个排队的请求都会持有套接字，在一些操作系统中，这将会消耗文件句柄。
 * 由于操作系统会限制进程打开的文件句柄数，因此最好限制下工作队列的大小。
 * 
 * @author Administrator
 *
 */
public class WorkQueue {
	public static ExecutorService newBoundedFixedThreadPool(int nThreads, int capacity) {
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(capacity),
				new ThreadPoolExecutor.DiscardPolicy());
	}

	public static void main() throws IOException {
		ServerSocket listener = new ServerSocket(8080);
		ExecutorService executor = newBoundedFixedThreadPool(4, 16);
		try {
			while (true) {
				Socket socket = listener.accept();
				executor.submit(new HandleRequestRunnable(socket));
			}
		} finally {
			listener.close();
		}
/*		这里我们没有直接使用Executors.newFixedThreadPool方法来创建线程池，而是自己构建了ThreadPoolExecutor对象，并将工作队列长度限制为16个元素。
		如果所有的线程都繁忙，新的任务将会填充到队列中，由于队列限制了大小为16个元素，如果超过这个限制，就需要由构造ThreadPoolExecutor对象时的最后一个参数来处理了。
		示例中，使用了抛弃策略（DiscardPolicy），即当队列到达上限时，将抛弃新来的任务。
		除此之外，还有中止策略（AbortPolicy）和调用者执行策略（CallerRunsPolicy）。前者将抛出一个异常，而后者会再调用者线程中执行任务。
		对于Web应用来说，最优的默认策略应该是抛弃或者中止策略，并返回一个错误给客户端（如HTTP 503错误）。
		当然也可以通过增加工作队列长度的方式，避免抛弃客户端请求，但是用户请求一般不愿意进行长时间的等待，且这样会更多的消耗服务器资源。
		工作队列的用途，不是无限制的响应客户端请求，而是平滑突发暴增的请求。通常情况下，工作队列应该是空的。*/
	}
}
/*提升性能：线程数调优
前面的示例展示了如何创建和使用线程池，但是，使用线程池的核心问题在于应该使用多少线程。首先，我们要确保达到线程上限时，不会引起资源耗尽。这里的资源包括内存（堆和栈）、打开文件句柄数量、TCP连接数、远程数据库连接数和其他有限的资源。特别的，如果线程任务是计算密集型的，CPU核心数量也是资源限制之一，一般情况下线程数量不要超过CPU核心数量。
由于线程数的选定依赖于应用程序的类型，可能需要经过大量性能测试之后，才能得出最优的结果。当然，也可以通过增加资源数的方式，来提升应用程序的性能。例如，修改JVM堆内存大小，或者修改操作系统的文件句柄上限等。然后，这些调整最终还是会触及理论上限。
利特尔法则
利特尔法则描述了在稳定系统中，三个变量之间的关系。

其中L表示平均请求数量，λ表示请求的频率，W表示响应请求的平均时间。举例来说，如果每秒请求数为10次，每个请求处理时间为1秒，那么在任何时刻都有10个请求正在被处理。回到我们的话题，就是需要使用10个线程来进行处理。如果单个请求的处理时间翻倍，那么处理的线程数也要翻倍，变成20个。
理解了处理时间对于请求处理效率的影响之后，我们会发现，通常理论上限可能不是线程池大小的最佳值。线程池上限还需要参考任务处理时间。
假设JVM可以并行处理1000个任务，如果每个请求处理时间不超过30秒，那么在最坏情况下，每秒最多只能处理33.3个请求。然而，如果每个请求只需要500毫秒，那么应用程序每秒可以处理2000个请求。
拆分线程池
在微服务或者面向服务架构（SOA）中，通常需要访问多个后端服务。如果其中一个服务性能下降，可能会引起线程池线程耗尽，从而影响对其他服务的请求。
应对后端服务失效的有效办法是隔离每个服务所使用的线程池。在这种模式下，仍然有一个分派的线程池，将任务分派到不同的后端请求线程池中。该线程池可能因为一个缓慢的后端而没有负载，而将负担转移到了请求缓慢后端的线程池中。
另外，多线程池模式还需要避免死锁问题。如果每个线程都阻塞在等待未被处理请求的结果上时，就会发生死锁。因此，多线程池模式下，需要了解每个线程池执行的任务和它们之间的依赖，这样可以尽可能避免死锁问题。
总结
即使没有在应用程序中直接使用线程池，它们也很有可能在应用程序中被应用服务器或者框架间接使用。Tomcat、JBoss、Undertow、Dropwizard等框架，都提供了调优线程池（servlet执行使用的线程池）的选项。
好了，老司机就说到这里。希望现在你可以对线程池有了一定的了解和兴趣，通过了解应用的需求，组合最大线程数和平均响应时间，可以得出一个合适的线程池配置。*/
