package com.util;

/**
 * @date 2017年10月16日下午12:31:02
 * @Description: TODO
 * @authorAdministrator
 */
public class Ts {
	//编号是静态变量初始化以后，它们已经有默认值了（但是没赋值，），代码的执行顺序。
public static void main(String[] args) {
	Singleton01 instance = Singleton01.getInstance();//1.
	System.out.println(instance.count1);//9
	System.out.println(instance.count2);//10
}
}
class Parent{
	public   Parent(){
		System.out.println("父类构造器");
	}
}
class Singleton01 extends Parent{ 
	//静态变量先初始化，后赋值。然后再执行静态代码块。
	private  static Singleton01 singleton=new Singleton01();//6 此句执行前，singleton已经初始化(singleton=null)，这里只是赋值。
	public  static int count1;//	count1不需要赋值，它已经初始化过了，
	public static int count2=0;//7  此句执行前，count2已经初始化，而且赋值过了(count2=1),这里重新给他赋值成0;该句执行后count2=0;
	static {
		count1=23;//8
	}
	public static Singleton01 getInstance(){//2
		return singleton;//
	}
	private Singleton01(){
		count1++;//3	count1=1;此句执行前，count1已经初始化(count1=0)
		count2++;//4    count2=1;此句执行前，count2已经初始化(count2=0)
		System.out.println("子类构造器");//5
	}
	
}