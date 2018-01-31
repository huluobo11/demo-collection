package com.map.test;

import org.junit.Test;

import com.map.Map;
import com.map.SmallMap;

/**
 * @date 2017年10月19日上午11:24:58
 * @Description:
 * @authorAdministrator
 */
public class TestMap {
	@Test
	public void testSmallMap() {
		long start = System.currentTimeMillis();
		Map<String, String> smallMap = new SmallMap<>(8);
		smallMap.put("name", "ss");
		smallMap.put("sex", "boy");
		smallMap.put("hobby", "girl");
		smallMap.put("grade", "7");
		smallMap.put("class", "15");
		smallMap.put("school", "14");
		smallMap.put("age", "12");
		smallMap.put("type", "1");
		smallMap.put("jiyou", "123");
		smallMap.put("aa", "11");
		smallMap.put("aa", "22");
		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");
		/*
		 * String object = smallMap.get("name"); System.out.println(object);
		 * String o = smallMap.get("aa"); System.out.println(o);
		 * System.out.println(smallMap.containsKey("sex"));
		 * System.out.println(smallMap.isEmpty());
		 */

	}

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		Map<String, Integer> smallMap = new SmallMap<>(8);
		int i = 0;
		while (i++ < 10) {
			smallMap.put(i + "-", i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");
		/*
		 * String object = smallMap.get("name"); System.out.println(object);
		 * String o = smallMap.get("aa"); System.out.println(o);
		 * System.out.println(smallMap.containsKey("sex"));
		 * System.out.println(smallMap.isEmpty());
		 */

	}
}
