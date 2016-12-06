package com.ygdxd.security.corsFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setHeader("Access-Control-Allow-Origin", "*");
	        if ("OPTIONS".equals(request.getMethod())) {
	            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
	            response.setHeader("Access-Control-Max-Age", "3600");
	            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, User-Agent");
	        }
	        filterChain.doFilter(request, response);
	}

}
