package com.hu.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:application-bean.xml"})
public class SpringXmlConfig {
    //这个类的作用就是让spring的监听器读取到application-bean.xml
}
