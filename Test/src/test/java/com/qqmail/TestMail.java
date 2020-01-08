package com.qqmail;

import org.junit.Test;

/**
 * @date 2017年8月16日
 * @author Administrator
 * @project demo
 */
public class TestMail {
	@Test
	public void test() {
		MailEntity entity = new MailEntity("1174963032@qq.com", "测试标题","测试内容");
		MailUtils.sendMail(entity);
	}
}
