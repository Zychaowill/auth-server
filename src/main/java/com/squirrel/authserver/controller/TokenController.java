package com.squirrel.authserver.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squirrel.authserver.authorization.annotation.Authorization;
import com.squirrel.authserver.authorization.annotation.CurrentUser;
import com.squirrel.authserver.authorization.manager.TokenManager;
import com.squirrel.authserver.authorization.model.TokenEntity;
import com.squirrel.authserver.constants.ResultStatus;
import com.squirrel.authserver.dao.entity.User;
import com.squirrel.authserver.dao.repository.UserRepository;
import com.squirrel.authserver.model.JsonEntity;
import com.squirrel.authserver.model.ResponseHelper;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenManager tokenManager;
	
	@RequestMapping(method = RequestMethod.POST)
	public JsonEntity<TokenEntity> login(@RequestParam String username, @RequestParam String password) {
		Objects.requireNonNull(username, "Username can not be empty!");
		Objects.requireNonNull(password, "Password can not be empty!");
		
		User user = userRepository.findByUsername(username);
		if (Objects.isNull(user) || !Objects.equals(user.getPassword(), password)) {
			return ResponseHelper.withErrorMessage(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
		}
		
		TokenEntity entity = tokenManager.create(user.getId());
		return ResponseHelper.of(entity);
	}
	
	@Authorization
	@RequestMapping(method = RequestMethod.DELETE)
	public JsonEntity<Boolean> logout(@CurrentUser User user) {
		tokenManager.delete(user.getId());
		return ResponseHelper.of(Boolean.TRUE);
	}
}
