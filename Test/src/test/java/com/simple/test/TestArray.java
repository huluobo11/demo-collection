package com.simple.test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @date 2017年9月1日
 * @author Administrator
 * @project Test
 */
public class TestArray implements CharSequence,Cloneable{
	@Test
	public void testM() {
		NumberFormat instance = NumberFormat.getInstance();
		String format = instance.format(13.562);
		System.out.println(format);
	}

	@Test
	public void teststr() {
		String string = "10a2b300c";
		char[] charArray = string.toCharArray();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isDigit(charArray[i])) {
				int k = 0;
				for (int j = i; j < charArray.length; j++) {
					if (Character.isDigit(charArray[j])) {
						k++;
					} else {
						String substring = string.substring(i, i + k);
						list.add(Integer.parseInt(substring));
						break;
					}
				}
			}
		}
		System.out.println(list);
		int result=0;
		for (Integer integer : list) {
			result+=integer;
		}
		System.out.println(result);
	}
	
	
	@Test
	public void Str(){
		try {
			System.out.println(1);
			//System.exit(0);
		} catch (Exception e) {
			
		}finally{
			System.out.println(2);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.CharSequence#length()
	 */
	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.CharSequence#charAt(int)
	 */
	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.CharSequence#subSequence(int, int)
	 */
	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
