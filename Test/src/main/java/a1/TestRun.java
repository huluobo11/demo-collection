package a1;

/**
 * @date 2017年9月25日上午11:07:09
 * @Description: TODO
 * @authorAdministrator
 */
public class TestRun {
	public static void main(String[] args) {
		 new Thread(new TestThread(),"线程1").start();
		 new Thread(new TestThread(),"线程2").start();
		 new Thread(new TestThread(),"线程3").start();
	}
}
