package com.simple.test;

/**
 * @date 2017年9月25日上午10:58:57
 * @Description: TODO
 * @authorAdministrator
 */
public class TestThread implements Runnable {
	private static int i = 1;

	@Override
	public void run() {
		while (i < 99) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (TestThread.class) {
				System.out.println(Thread.currentThread().getName() + "---" + i);
				i--;
			}
		}
	}

}
