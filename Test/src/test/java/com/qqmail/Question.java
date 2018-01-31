package com.qqmail;
import org.junit.Test;

/**
 * @date 2017年11月3日上午11:46:07
 * @Description:
 * @authorAdministrator
 */
public class Question {
	@Test
	public void S() {
		int x = 19;
		int sum=x;
		int t=x/3;
		int v=t+x%3;
		sum+=t;
		while (t>2) {
			t=v/3;
			 v=t+x%3;
			 sum+=t;
		}
		System.out.println(sum);
	}
}
