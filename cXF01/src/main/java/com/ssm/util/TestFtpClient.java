package com.ssm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

/**
 * @date 2017年7月6日
 * @author Administrator
 * @project xFire
 */
public class TestFtpClient {
	@Test
	public void testUpload() {
		FTPClient ftp = new FTPClient();
		// 链接远程服务
		try {
			ftp.connect("115.159.149.66", 21);
			ftp.login("ftp", "Lqy199685");
			// 返回登录结果状态
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println(reply);
				ftp.disconnect();
				return;
			}
			 //修改上传文件路径
            ftp.changeWorkingDirectory("/root/smallWhite/imgs/");
            //修改文件类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
          //获取上传文件的输入流
            FileInputStream fileInputStream = new FileInputStream(new File("D:/123.jpg"));
            //把文件推到服务器上
            ftp.storeFile("hello.jpg", fileInputStream);
          //退出登录
            ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
