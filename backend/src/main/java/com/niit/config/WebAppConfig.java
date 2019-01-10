package com.niit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc // <mvc:annotation-driven></mvc:annotation-driven>in dispatcher-servlet.xml
@ComponentScan(basePackages="com.niit") //<context:component-scan> in dispatcher-servlet.xml

public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	 public WebAppConfig()
	 {
		 System.out.println("WebConfiguration class is loaded and instantiated");
	 }

	 @Bean(name = "multipartResolver")
	 public CommonsMultipartResolver commonsMultipartResolver(){
	     CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	     return commonsMultipartResolver;

	 }
	

}
