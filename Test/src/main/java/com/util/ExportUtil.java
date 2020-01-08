
package com.util;


import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @date 2017年8月18日
 * @author Administrator
 * @project FixedAssets
 */
public class ExportUtil {
	public  Workbook workbook;
	public  Sheet sheet;
	 {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
	}

	/**
	 * 生成excel文件
	 * 
	 * @param strArray
	 *            列标题组成的数组
	 * @param list
	 *            内容List          
	 * @param out
	 *            标准输出流
	 * @return 是否写入成功
	 */
	public  boolean writeFile(String[] strArray, List<?> list, OutputStream out,String[]flelds) {
		List<String>ignoreFields=Arrays.asList(flelds);
		boolean result = true;
		if (list == null || list.size() < 1) {
			System.err.println("list参数不合法！");
			return false;
		}
		if (null==out) {
			System.err.println("out参数不可用！");
			return false;
		}
		if (list.get(0) instanceof Map) {
			List<Map<String, Object>> mapList = (List<Map<String, Object>>) list;
			result = writeWorkBookByMap(strArray, mapList,ignoreFields);
		} else {
			result = writeWorkBookByEntity(strArray, list,ignoreFields);
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				result = false;
			}
		}
		return result;
	}

	/**
	 * 生成excel文件
	 * 
	 * @param list
	 *            内容List
	 * @param out
	 *            标准输出流
	 * @return 是否写入成功
	 */
	public  boolean writeFile(List<?> list, OutputStream out,List<String>ignoreFields) {
		boolean result = true;
		if (list == null || list.size() < 1) {
			System.err.println("list参数不合法！");
			return false;
		}
		if (null==out) {
			System.err.println("out参数不可用！");
			return false;
		}
		if (list.get(0) instanceof Map) {
			List<Map<String, Object>> mapList = (List<Map<String, Object>>) list;
			result = writeWorkBookByMap(null, mapList,ignoreFields);
		} else {
			result = writeWorkBookByEntity(null, list,ignoreFields);
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				result = false;
			}
		}
		return result;
	}

	/**
	 * 根据List<实体类> 生成workbook
	 * 
	 * @param strArray
	 *            列标题数组
	 * @param list
	 * @param fieldStrings 字段(不在excel中出现的字段)
	 */
	private  boolean writeWorkBookByEntity(String[] strArray, List<?> list,List<String>fieldStrings) {
		Row row2 = null;
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			Class<? extends Object> class1 = obj.getClass();
			Field[] fields = class1.getDeclaredFields();
		String [] fieldNameStrings=new String[fields.length];
		for (int j = 0; j < fields.length; j++) {
			fieldNameStrings[j]=fields[j].getName();
		}
		//类中的字段名组成的list
		List<String> asList = Arrays.asList(fieldNameStrings);
			Row row = sheet.createRow(0);
			// 用自定义的列标题。
			if (strArray != null && strArray.length > 0) {
				for (int k = 0; k < strArray.length; k++) {
					row.createCell(k).setCellValue(strArray[k]);
				}
			}
			row2 = sheet.createRow(i + 1);
			for (int j = 0; j < fieldStrings.size(); j++) {
				// 以类的属性名作为结果表格的列标题
				if (strArray == null || strArray.length < 1) {
					row.createCell(j).setCellValue(fieldStrings.get(j));
				}
				try {
					boolean contains = asList.contains(fieldStrings.get(j));
					
						if(!contains)  { continue;}
					Method method = class1.getMethod(getMethodName("get", fieldStrings.get(j)));
					Object returnVal = method.invoke(obj);
					//对日期进行处理
					if (returnVal instanceof Date &&returnVal!=null ) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						returnVal = simpleDateFormat.format(returnVal).trim();
					}
					row2.createCell(j).setCellValue(String.valueOf(returnVal));
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
				| InvocationTargetException e) {
					e.printStackTrace();
					return false;
				}

			}
		}
		return true;
	}

	/**
	 * 根据List<Map> 生成workbook
	 * 
	 * @param strArray
	 * @param list
	 */
	private  boolean writeWorkBookByMap(String[] strArray, List<Map<String, Object>> list,List<String>ignoreFields) {
		Row row2 = null;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Row row = sheet.createRow(0);
			if (strArray != null && strArray.length > 0) {
				for (int k = 0; k < strArray.length; k++) {
					row.createCell(k).setCellValue(strArray[k]);
				}
			}
			row2 = sheet.createRow(i + 1);
			int j = 0;
			for (String field : ignoreFields) {
				if (strArray == null || strArray.length < 1) {
					row.createCell(j).setCellValue(field);
				}
				try {
					Object obj = map.get(field);
					if (obj instanceof Date &&obj!=null ) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						obj = simpleDateFormat.format(obj).trim();
					}
					row2.createCell(j).setCellValue(String.valueOf(obj));
				} catch (IllegalArgumentException | SecurityException e) {
					e.printStackTrace();
					return false;
				}
				j++;
			}
		}
		return true;
	}

	/**
	 * 获取方法名
	 * 
	 * @param head
	 *            一般为get
	 * @param tail
	 *            字段名
	 * @return 方法名
	 */
	private  String getMethodName(String head, String tail) {
		char[] ch = tail.toCharArray();
		if ((ch[0] >= 'a') && (ch[0] <= 'z')) {
			ch[0] = ((char) (ch[0] - ' '));
		}
		return head + new String(ch);
	}
}