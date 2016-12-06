package com.ygdxd.security.confg;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration{
	
	protected MethodSecurityExpressionHandler createExpressionHandler(){
		return new OAuth2MethodSecurityExpressionHandler();
	}

}
