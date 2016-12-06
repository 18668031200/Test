package com.ygdxd.resource.errorBuilder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

public abstract class BaseErrorBuilder{
	
	private Integer code;
	
	private String message;
	
	private String request;
	
	public BaseErrorBuilder(Integer code,String message){
		this.code=code;
		this.message=message;
	}
	
	public BaseErrorBuilder setRequest(RequestMethod requestMethod,String url){
		this.request=String.format("%s %s", requestMethod.name(),url);
		return this;
	}
	
	public JSONObject build(){
		JSONObject result=new JSONObject();
		result.put("code", this.code);
		result.put("message", this.message);
		if (StringUtils.isNotBlank(request)) {
			result.put("request", request);
		}
		return result;
	}

}
