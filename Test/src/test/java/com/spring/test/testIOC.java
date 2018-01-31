
package com.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.pojo.Item;

/**
 * @date 2017年8月25日
 * @author Administrator
 * @project Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-test.xml")
public class testIOC {
	@Autowired
	private Item item;

	@Test
	public void testItemNew() {
		System.out.println(item);
	}
}
