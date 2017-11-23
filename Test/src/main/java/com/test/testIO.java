package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * @date 2017年9月5日
 * @author Administrator
 * @project Test
 */
public class testIO {
	@Test
	public void testDown(){
		String s="你是SB吗？";
		File file = new File("D:\\test.txt");
		try {
			OutputStream fileOutputStream = new  FileOutputStream(file);
			fileOutputStream.write(s.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfile() throws IOException {// 读取txt文件
		File file = new File("D:\\test.txt");
		// 第一种复制文件。
		/*
		 * Reader inputStreamReader = new InputStreamReader(new
		 * FileInputStream(file)); BufferedReader bufferedReader = new
		 * BufferedReader(inputStreamReader); String lineTxt = null;
		 * StringBuffer sb = new StringBuffer(); while ((lineTxt =
		 * bufferedReader.readLine()) != null) { sb.append(lineTxt); }
		 * System.out.println(sb.toString()); bufferedReader.close();
		 */
		// 第二种
		// FileUtils.copyFile(file, destFile);
		// 第三种
		IOUtils.copy(new FileInputStream(file), System.out);
		// 只读不写，
		String result = FileUtils.readFileToString(file, "utf-8");
	}

	@Test
	public void testDeleteFile() throws IOException {
		String dir = "D:/test";
		File file = new File(dir);
		if (!file.isDirectory()) {
			System.out.println("不是目录 ！");
		} else {
			delete(file);
		}
	}

	public void delete(File f) {
		File[] listFiles = f.listFiles();
		for (File file : listFiles) {
			if (!file.isDirectory()) {
				file.delete();
			} else {
				delete(file);
			}
		}
		f.delete();
	}

}
