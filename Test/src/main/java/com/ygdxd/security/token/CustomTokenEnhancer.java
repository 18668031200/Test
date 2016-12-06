package com.ygdxd.security.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		final Object principal = authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

        if (principal instanceof User) {
        	User user = (User) principal;
        	additionalInfo.put("uid", user.getUsername());
        	
        	Map<String, Map<String, String>> _links = new HashMap<>();
        	Map<String, String> userInfoLink = new HashMap<>();
        	//userInfoLink.put("href", ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserController.class).))
        	_links.put("self", userInfoLink);
        	additionalInfo.put("_links", _links);
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		
		return accessToken;
	}

}
