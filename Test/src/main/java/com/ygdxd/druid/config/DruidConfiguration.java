package com.ygdxd.druid.config;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.ygdxd.druid.multids.DynamicDataSource;



@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfiguration {
	
	private static final Logger log=org.slf4j.LoggerFactory.getLogger(DruidConfiguration.class);
	
	@Bean(name = "dataSource1", initMethod = "init", destroyMethod = "close")
	public DataSource dataSource1(DataSourceProperties dataSourceProperties) throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		dataSource.setUrl(dataSourceProperties.getUrl());
		dataSource.setUsername(dataSourceProperties.getUsername());
		dataSource.setPassword(dataSourceProperties.getPassword());
		// ��ʼ����С����С�����
		dataSource.setInitialSize(5);
		dataSource.setMinIdle(5);
		dataSource.setMaxActive(20);
		// ���û�ȡ���ӵȴ���ʱ��ʱ��
		dataSource.setMaxWait(60000);
		// ���ü����òŽ���һ�μ�⣬�����Ҫ�رյĿ������ӣ���λ�Ǻ���
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		// ����һ�������ڳ�����С�����ʱ�䣬��λ�Ǻ���
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("select 1");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		// ��PSCache������ָ��ÿ��������PSCache�Ĵ�С
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		// ���ü��ͳ�����ص�filters��ȥ�����ؽ���sql�޷�ͳ�ƣ�'wall'���ڷ���ǽ
		dataSource.addFilters("stat,wall,log4j");
		// ͨ��connectProperties��������mergeSql���ܣ���SQL��¼
		dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");

		return dataSource;
	}
	
	@ConditionalOnProperty(name = "spring.datasource2.enable")
	@Bean(name="dataSource2", initMethod = "init", destroyMethod = "close")
	public DataSource dataSourc2(DataSourceProperties dataSourceProperties) throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		dataSource.setUrl(dataSourceProperties.getUrl());
		dataSource.setUsername(dataSourceProperties.getUsername());
		dataSource.setPassword(dataSourceProperties.getPassword());

		// ��ʼ����С����С�����
		dataSource.setInitialSize(5);
		dataSource.setMinIdle(5);
		dataSource.setMaxActive(20);
		// ���û�ȡ���ӵȴ���ʱ��ʱ��
		dataSource.setMaxWait(60000);
		// ���ü����òŽ���һ�μ�⣬�����Ҫ�رյĿ������ӣ���λ�Ǻ���
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		// ����һ�������ڳ�����С�����ʱ�䣬��λ�Ǻ���
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("select 1");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		// ��PSCache������ָ��ÿ��������PSCache�Ĵ�С
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		// ���ü��ͳ�����ص�filters��ȥ�����ؽ���sql�޷�ͳ�ƣ�'wall'���ڷ���ǽ
		dataSource.addFilters("stat,wall,log4j");
		// ͨ��connectProperties��������mergeSql���ܣ���SQL��¼
		dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");

		return dataSource;
	}
	@Bean
	@Primary
	public DynamicDataSource dataSource(@Autowired DataSource dataSource1, @Autowired DataSource dataSource2) {
		DynamicDataSource resolver = new DynamicDataSource();

		Map<Object, Object> dataSources = new HashMap<>();
		dataSources.put("dataSource1", dataSource1);
		dataSources.put("dataSource2", dataSource2);

		resolver.setTargetDataSources(dataSources);
		resolver.setDefaultTargetDataSource(dataSource1);

		return resolver;
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("allow", "127.0.0.1");
		initParameters.put("loginUsername", "dbsysuser");
		initParameters.put("loginPassword", "dbsysuserPwd");
		bean.setInitParameters(initParameters);

		return bean;
	}

}
