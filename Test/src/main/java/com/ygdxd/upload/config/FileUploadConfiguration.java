package com.ygdxd.upload.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class FileUploadConfiguration {
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory=new MultipartConfigFactory();
		factory.setMaxFileSize("500MB");
		factory.setMaxRequestSize("500MB");
		return factory.createMultipartConfig();
	}
	@Bean
	public MultipartResolver multipartResolver(){
		StandardServletMultipartResolver servletMultipartResolver=new StandardServletMultipartResolver();
		return servletMultipartResolver;
	}


}
