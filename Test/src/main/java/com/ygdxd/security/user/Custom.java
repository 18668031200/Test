package com.ygdxd.security.user;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class Custom extends User{
	
	@Getter private String id;
	
	public Custom(String id,String username,String password,Collection<? extends GrantedAuthority> authorities){
		super(username, password, authorities);
		this.id = id;
	}
	
	public Custom(String id, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
	}
}
