package com.ygdxd.mybatis.typeHanler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringAraayTypeHandler extends BaseTypeHandler<String[]>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		StringBuilder result=new StringBuilder();
		for(String value : parameter)
			result.append(value).append(".");
		result.deleteCharAt(result.length()-1);
		ps.setString(i,result.toString());
	}

	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String columnValue = rs.getString(columnName);
		return this.getStringArray(columnValue);
	}

	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String columnValue = rs.getString(columnIndex);
		return this.getStringArray(columnValue);
	}

	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String columnValue = cs.getString(columnIndex);
		return this.getStringArray(columnValue);
	}
	
	private String[] getStringArray(String columnValue) {
		if (columnValue == null)
			return null;
		return columnValue.split(".");
	}

}
