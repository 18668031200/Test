package com.ygdxd.app.User;

import com.ygdxd.resource.errorBuilder.BaseErrorBuilder;

public class UserErrorBuilder extends BaseErrorBuilder{
	
	public static final Integer Error_User_UserNameNoPoint=114001;
	
	public static final Integer Error_User_PasswordNoPoint=114002;
	
	public static final Integer Error_User_UserUpdateFailure=114003;

	public UserErrorBuilder(Integer code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

}
