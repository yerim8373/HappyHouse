package com.ssafy.happyhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Configuration
//@EnableWebMvc
//@ComponentScan("com.ssafy.happyhouse.controller")
public class MVCConfig {//implements WebMvcConfigurer{
	
	/* <resources mapping="/resources/**" location="/resources/" /> */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/views/img/");
//		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
//		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
//	}
	
//	@Bean
//	public ViewResolver viewResolver() {
//	      InternalResourceViewResolver irvr = new InternalResourceViewResolver();
//	      irvr.setPrefix("/WEB-INF/views/");
//	      irvr.setSuffix(".jsp");
//	      return irvr;
//	}
	
//	@Bean
//	public BeanNameViewResolver beanNameViewResolver() {
//		BeanNameViewResolver br = new BeanNameViewResolver();
//		br.setOrder(0);
//		return br;
//	}
}