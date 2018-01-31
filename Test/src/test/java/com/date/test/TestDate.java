package com.date.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

/**
 * @date 2017年11月15日下午4:55:59
 * @Description:
 * @authorAdministrator
 */
public class TestDate {
	@Test
	public void ss() throws ParseException {
		//String string = "1998-03-03";//输入的日期。  
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = simpleDateFormat.parse(string);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int i=45;//输入的保质期的天数。
		calendar.add(Calendar.DATE, i);
		Date date2 = calendar.getTime();
		String format = simpleDateFormat.format(date2);
		System.out.println(format); 
		scanner.close();
	}
	
/*	public void actionPerformed(ActionEvent e) {
		String  str2=text1.getText();
		String []nums=new String[str2.length()];
		for(int i=0;i<str2.length();i++){
			char ch=str2.charAt(i);
			n=(int)ch;
			
			String ss=Integer.toBinaryString(n);	        
			System.out.println(Integer.toBinaryString(n));
			
			nums[i]=Integer.toBinaryString(n);
			}

}*/
}
