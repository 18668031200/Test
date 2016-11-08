package com.ygdxd.druid.multids;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DynamicDataSourceAspect {

private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	
	@Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.setDataSourceType(ds.name());
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
	

}
