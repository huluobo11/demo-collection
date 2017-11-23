package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @date 2017年7月6日
 * @author Administrator
 * @project xFire
 */
public class TestFtpClient {
	public static void main(String args[]) {
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
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			
			
			 //修改上传文件路径
            boolean isSuccess = ftp.changeWorkingDirectory("/home/imgs");
            System.out.println("isChanged=="+isSuccess);
            //修改文件类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            ftp.setFileTransferMode(ftp.STREAM_TRANSFER_MODE);
            ftp.setControlEncoding("UTF-8");
			ftp.setBufferSize(1024); //设置1M缓冲
          //获取上传文件的输入流
            FileInputStream fileInputStream = new FileInputStream(new File("D:/123.jpg"));
            //把文件推到服务器上    
            String fileName="123.jpg";         
            boolean storeFile = ftp.storeFile(new String(fileName.getBytes("UTF-8"),"iso-8859-1"),fileInputStream);
          //退出登录
            int reply2 = ftp.getReplyCode();
            ftp.logout();
            System.out.println("______________"+storeFile);
            System.out.println(reply2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
