package com.ssm.util;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * dataSource类
 * 和<bean id="dataSource">中的class位置对应。
 * @date 2017年6月27日
 * @author Administrator
 * @project xFire
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		 System.out.println(this.getClass().getName()+"此时获取到的数据源为："+DataSourceContextHolder.getDbType());
	        return DataSourceContextHolder.getDbType();  
	}
	@Override  
    public void setTargetDataSources(Map targetDataSources) {  
        super.setTargetDataSources(targetDataSources);  
        //重点  
        super.afterPropertiesSet();  
    }
}
