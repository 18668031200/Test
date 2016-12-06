package com.ygdxd.mybatis.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@AutoConfigureAfter(MyBatisConfiguration.class)
public class MyBatisMapperScannerConfig {
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("SqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.ygdxd.app");
		Properties properties = new Properties();
	    properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.MySqlMapper,tk.mybatis.mapper.common.IdsMapper");
	    properties.setProperty("notEmpty", "false");
	    properties.setProperty("IDENTITY", "select uuid()");
	    properties.setProperty("ORDER", "BEFORE");
	    mapperScannerConfigurer.setProperties(properties);
	    return mapperScannerConfigurer;
	}

}
