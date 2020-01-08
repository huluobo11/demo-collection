/**
 * 
 */
package com.ssm.util;

import org.springframework.stereotype.Component;

/**
 *  dataSource的处理类。
 * @date 2017年6月27日
 * @author Administrator
 * @project xFire
 */
@Component
public class DataSourceContextHolder {
	public static final String DATA_SOURCE_A = "dataSourceA";
	public static final String DATA_SOURCE_B = "dataSourceB";
	//public static final String DATA_SOURCE_C = "dataSourceC";
	// 线程本地环境
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	// 设置数据源类型
	public static void setDbType(String dbType) {
		String clazzName = new SecurityManager() {
			public String getClassName() {
				return getClassContext()[1].getName();
			}
		}.getClassName();
		System.out.println(clazzName + "此时切换的数据源为---：" + dbType);
		contextHolder.set(dbType);
	}

	// 获取数据源类型
	public static String getDbType() {
		return (contextHolder.get());
	}

	// 清除数据源类型
	public static void clearDbType() {
		contextHolder.remove();
	}
}
