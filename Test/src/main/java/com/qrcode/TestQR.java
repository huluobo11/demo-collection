package com.qrcode;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * @date 2017年8月16日
 * @author Administrator
 * @project demo
 */
public class TestQR {
	@Test
	public void test() {
		String content = "http://www.baidu.com";
		String file = "E:/AA/test.png";
		// 使用默认大小的png格式生成二维码
		File file2 = new File(file);
		if (!file2.getParentFile().exists()) {
			file2.getParentFile().mkdirs();
		}
		try {
			if (!file2.exists()) {
				file2.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		QRcodeUtil.createPngQRcodeDefaultSize(content, file);
	}
}
