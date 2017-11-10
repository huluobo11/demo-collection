package a1;

/**
 * @date 2017年9月25日上午10:18:36
 * @Description: 单例模式
 * @authorAdministrator
 */
public class Singleton {
	private Singleton() {
		// 私有化的构造函数、
	}
//使用静态内部类。
	private static class LazyHolder {
		private static final Singleton INSTANCE = new Singleton();
	}

	public static final Singleton getInstance() {  
		return LazyHolder.INSTANCE;
	}
}
