package com.ygdxd.druid.multids;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.ygdxd.druid.multids.DynamicDataSourceContextHolder;



public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		 return DynamicDataSourceContextHolder.getDataSourceType();
	}

}
