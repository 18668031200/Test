package com.ygdxd.security.confg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.ygdxd.app.User.User;
import com.ygdxd.security.corsFilter.CorsFilter;
import com.ygdxd.security.entryPoint.RestAuthenticationEntryPoint;
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
//		http.csrf().disable();
//		http.authorizeRequests().anyRequest().denyAll();
//		http.authorizeRequests().anyRequest().authenticated();
		http 
        .authorizeRequests()  
           .antMatchers("/login.html","/login/failure").permitAll()    
           .anyRequest().denyAll()
            .and()  
//        .exceptionHandling()  
//            .accessDeniedPage("/login?authorization_error=true")  
//            .and()  
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
	
	
	@Order(3)
	@Configuration
	@EnableResourceServer
	public static class ApiResources extends ResourceServerConfigurerAdapter {
		
		@Autowired
	    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		@Autowired
	    private TokenStore tokenStore;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources
			.authenticationEntryPoint(restAuthenticationEntryPoint)
			.tokenStore(tokenStore);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			.antMatcher("/ygdxd/**")
			.exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint)
				.and()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.logout();
		}
		
		
	}
	
}
