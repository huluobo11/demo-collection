package com.hu;

import com.hu.configuration.Wisely2Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication(scanBasePackages = {"com.hu"})
//(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan // 注意要加上@ServletComponentScan注解，否则Servlet无法生效
@EnableConfigurationProperties(Wisely2Settings.class)
public class Springboot01Application {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		        factory.setMaxFileSize("128KB"); //KB,MB
		/// 设置总上传数据总大小
		        factory.setMaxRequestSize("256KB");
		//Sets the directory location where files will be stored.
		//factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        //设置启动时的banner为关闭状态，
        /*SpringApplication application = new SpringApplication(Springboot01Application.class);
		application.setBannerMode(Banner.Mode.OFF);*/
        SpringApplication.run(Springboot01Application.class, args);
    }
}
