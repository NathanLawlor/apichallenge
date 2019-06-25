package com.challenge.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	public AuthenticationInterceptor() { }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean isHeaderPresent = (request.getHeader("Api-Key") != null);
		Boolean isKeyValid = false;
		
		if(isHeaderPresent && request.getHeader("Api-Key").equals("5634563846456")) {
			isKeyValid = true;
		}
		else {
			response.getWriter().append("No API-KEY found");
		}
		
		System.out.println("api-key: " + isKeyValid);
		
		return isKeyValid;
	}
	
}
