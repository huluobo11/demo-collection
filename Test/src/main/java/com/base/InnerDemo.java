package com.base;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.base.Outter.Inner;
import com.base.Outter.Inner2;

public class InnerDemo {
	public static void main(String[] args) {
		Outter i = new Outter();
		i.testInner(i.new Inner());
		i.new Inner().showName();
		new Inner2() {
			@Override
			public void method1(int a, int b) {
				System.out.println(a - b);
			}
		};
		Inner inner = Outter.getInner();
		inner.showName();
		Inner2 inner2 = Outter.getInner2();
		inner2.method1(3, 2);
	}
}

class Outter {
	private int num = 0;

	void testInner(Inner in) {
		System.out.println(in);
	};

	interface Inner2 {
		void method1(int a, int b);
	}

	public static Inner getInner() {
		return new Outter().new Inner();
	};

	public static Inner2 getInner2() {
		// this.getClass();
		return new Inner2() {
			@Override
			public void method1(int a, int b) {
				System.out.println(a + b);
			}
		};
	};

	public class Inner {

		public void showName() {
			System.out.println(num);
		};

	}

	static class Inner3 {
		public void method2() {
			System.out.println(this.getClass().getName());
		}
	}

}
