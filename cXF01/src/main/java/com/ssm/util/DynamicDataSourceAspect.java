package com.ssm.util;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Aspect
@Order(1) 
public class DynamicDataSourceAspect {
	 @Pointcut("execution (* com.ssm.service.impl.*.*(..))")
	     private void anyMethod(){} //声明一个切入点,切入点的名称其实是一个方法
	@Before("anyMethod()")
    public void testBefore(JoinPoint point){
        //获得当前访问的class
		System.out.println();
		
        Class<?> className = point.getTarget().getClass();
        System.out.println(className);
        DynamicDataSourceAnnotation dataSourceAnnotation = className.getAnnotation(DynamicDataSourceAnnotation.class);
        if (dataSourceAnnotation != null ) {
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            String dataSource = DataSourceContextHolder.DATA_SOURCE_A;
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DynamicDataSourceAnnotation.class)) {
                    DynamicDataSourceAnnotation annotation = method.getAnnotation(DynamicDataSourceAnnotation.class);
                    dataSource = annotation.dataSource();
                    System.out.println("DataSource Aop ====> "+dataSource);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DataSourceContextHolder.setDbType(dataSource);
        }

    }

    @After("anyMethod()")   //后置通知
    public void testAfter(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        System.out.println(className);
        DynamicDataSourceAnnotation dataSourceAnnotation = className.getAnnotation(DynamicDataSourceAnnotation.class);
        if (dataSourceAnnotation != null ) {
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            System.out.println(methodName);
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            String dataSource = DataSourceContextHolder.DATA_SOURCE_A;
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DynamicDataSourceAnnotation.class)) {
                    DynamicDataSourceAnnotation annotation = method.getAnnotation(DynamicDataSourceAnnotation.class);
                    dataSource = annotation.dataSource();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(dataSource != null && !DataSourceContextHolder.DATA_SOURCE_A.equals(dataSource)) DataSourceContextHolder.clearDbType();
        }
    }
}
