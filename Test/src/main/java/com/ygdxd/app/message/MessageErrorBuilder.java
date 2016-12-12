package com.ygdxd.app.message;

import com.ygdxd.resource.errorBuilder.BaseErrorBuilder;

public class MessageErrorBuilder extends BaseErrorBuilder{
	
	public static final Integer Error_Message_SendFailure=114201;
	
	public static final Integer Error_Message_VerificationCodeNullPoint=114202;
	
	/**
	 * 过期
	 */
	public static final Integer Error_Message_TimeLimit=114203;
	
	/**
	 * 验证码错误
	 */
	public static final Integer Error_Message_VerificationCodeWrong=114004;

	public MessageErrorBuilder(Integer code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

}
