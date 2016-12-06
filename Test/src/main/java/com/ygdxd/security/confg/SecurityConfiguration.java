package com.ygdxd.security.confg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.ygdxd.app.User.User;
import com.ygdxd.security.corsFilter.CorsFilter;
import com.ygdxd.security.user.springUserDetailService;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	springUserDetailService userDetailsService;
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(User.PASSWORD_ENCODER);
		
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(
					  "/druid/**",
					  "/resources/**",
					  "/error/**"
			);
	}

	
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
		http.csrf().disable();
		http.authorizeRequests().anyRequest().denyAll();
		http 
        .authorizeRequests()  
            .antMatchers("/login.html","/login/failure").permitAll()    
            .and()  
        .exceptionHandling()  
            .accessDeniedPage("/login?authorization_error=true")  
            .and()  
        // TODO: put CSRF protection back into this endpoint  
        .csrf()  
            .disable()   
        .formLogin()  
            .loginPage("/login.html")//登陆页面  
            .loginProcessingUrl("/login")//登陆处理路径  
            .usernameParameter("username")//登陆用户名参数  
            .passwordParameter("password")//登陆密码参数  
			.defaultSuccessUrl("/login/success")//登陆成功路径  
			.failureUrl("/login/failure");//登陆失败路径  
	}
	
}
