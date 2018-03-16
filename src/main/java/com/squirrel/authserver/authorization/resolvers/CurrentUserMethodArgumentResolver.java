package com.squirrel.authserver.authorization.resolvers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.squirrel.authserver.authorization.annotation.CurrentUser;
import com.squirrel.authserver.constants.TokenConstants;
import com.squirrel.authserver.dao.entity.User;
import com.squirrel.authserver.dao.repository.UserRepository;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.getParameterType().isAssignableFrom(User.class)
				&& parameter.hasParameterAnnotation(CurrentUser.class))
			return true;
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Long currentUserId = (Long) webRequest.getAttribute(TokenConstants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
		if (Objects.nonNull(currentUserId))
			return userRepository.findOne(currentUserId);
		throw new MissingServletRequestPartException(TokenConstants.CURRENT_USER_ID);
	}

}
