package com.squirrel.authserver.authorization.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.squirrel.authserver.authorization.annotation.Authorization;
import com.squirrel.authserver.authorization.manager.TokenManager;
import com.squirrel.authserver.authorization.model.TokenEntity;
import com.squirrel.authserver.constants.TokenConstants;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenManager manager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod))
			return true;
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		String authorization = request.getHeader(TokenConstants.AUTHORIZATION);
		TokenEntity entity = manager.get(authorization);
		if (manager.check(entity)) {
			request.setAttribute(TokenConstants.CURRENT_USER_ID, entity.getUserId());
			return true;
		}
		if (method.getAnnotation(Authorization.class) != null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return true;
	}
}
