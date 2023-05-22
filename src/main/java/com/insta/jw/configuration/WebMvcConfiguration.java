package com.insta.jw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.insta.jw.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
//	파일 업로드 리졸버
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
		
		return commonsMultipartResolver;
	}
	
//	외부 이미지 경로 핸들러
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//업로드한 파일 저장 경로
		registry.addResourceHandler("/images/**").
		addResourceLocations("file:///C:/JSP/workspace-STS4.12.1/InstaClone/images/");

		//기본 저장 경로
		registry.addResourceHandler("/img/**").
		addResourceLocations("file:///C:/JSP/workspace-STS4.12.1/InstaClone/src/main/resources/static/images/");
	}
	
	
	
//	로그인 세션 인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
		.addPathPatterns("/insta/**")
		.excludePathPatterns("/insta/login")
		.excludePathPatterns("/insta/signup");
	}
}
