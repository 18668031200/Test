package com.ygdxd.mybatis.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageHelper;

@Configuration
@EnableTransactionManagement
public class MyBatisConfiguration  implements TransactionManagementConfigurer{
	
	@Autowired DataSource dataSource;
	
	@Bean(name="SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "false");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		
		// 添加插件
		sqlSessionFactoryBean.setPlugins(new org.apache.ibatis.plugin.Interceptor[]{pageHelper});
		
		return sqlSessionFactoryBean.getObject();
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return new DataSourceTransactionManager();
	}

}
