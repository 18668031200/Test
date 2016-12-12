package com.ygdxd.security.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class springUserDetailService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(springUserDetailService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
		String[] roles=new String[]{"ADMIN:ALL"};
		return new Custom("AAA", username, bcpe.encode("xiaoming"), AuthorityUtils.createAuthorityList(roles) );
	}

}
