/**
 * 分析一下这个程序的输出
 *
 */

package yxxy.c_005;

public class T implements Runnable {

	private int count = 10;
	
	@Override
	public /*synchronized*/ void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<5; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}
	
}
