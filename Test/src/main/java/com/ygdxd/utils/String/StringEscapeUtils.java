package com.ygdxd.utils.String;

import org.apache.commons.lang3.StringUtils;

public class StringEscapeUtils extends org.apache.commons.lang3.StringEscapeUtils {

	public static String escapeSql(String str) {
		return StringUtils.replace(str, "'", "''");
	}
	
}
