package com.ygdxd.utils.pattern;

import java.util.regex.Pattern;

public class RegexValidator {
	
	/**
	 * 手机号
	 */
	public static final String MOBILE = "/^1[0-9]{10}$/";
	
	/**
	 * 是否是手机格式
	 * 
	 * @param mobile
	 * @return
	 */
	public static Boolean isMobile(String mobile){
		return Pattern.matches(MOBILE, mobile);
	}
	
}
