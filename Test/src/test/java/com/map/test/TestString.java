package com.map.test;

import java.util.HashSet;

import org.junit.Test;

/**
 * @date 2017年10月19日下午2:47:43
 * @Description: TODO
 * @authorAdministrator
 */
public class TestString {
	@Test
	public void testCount() {
		System.out.println(1 << 3 - 2);
	}

	public int jieshen(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++) {
			result = result * i;
		}
		return result;
	}

	@Test
	public void testStr() {
		String string = "abs";
		int randomNum = 1;
		while (randomNum < string.length() + 1) {
			getRandom(string, randomNum);
			randomNum++;
		}
	}

	public void getRandom(String string, int randomNum) {
		char[] charArray = string.toCharArray();
		int topicSize = string.length();
		int count = jieshen(topicSize) / jieshen(topicSize - randomNum);
		int[] charRemember;// 保证每个字符取出不放回。
		HashSet<String> hashSet = new HashSet<>();
		int index = 0;
		while (index > -1 && index < count) {
			charRemember = new int[topicSize];
			// System.out.println("----第"+(index+1)+"次---------取"+randomNum+"个字符-------");
			String result = "";
			for (int i = 0; i < randomNum; i++) {
				int ranN = (int) (Math.random() * topicSize);
				if (charRemember[ranN] == 1) {
					i--;
					continue;
				}
				result += charArray[ranN];
				charRemember[ranN] = 1;
			}
			if (hashSet.contains(result)) {
				index--;
				continue;
			}
			hashSet.add(result);
			System.out.println(result);
			index++;
		}
		charRemember = null;
	}

	public void testme() {
		String s = "wewq";
		fun(s, "");
	}

	public static void main(String args[]) {
		TestString testString = new TestString();
		testString.fun("web", "");

	}

	public void fun(String string, String s) {
		if (string.length() <= 0) {
			System.out.println(s);
			return;
		}
		for (int i = 0; i < string.length(); i++) {
			char c = string.toString().charAt(i);
				if (i < string.length()) {
					fun(string.substring(0, i) + string.substring(i + 1), s + c);
				} else {
					System.out.println(s + c);
				}
			}
		}

}
