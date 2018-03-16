package com.squirrel.authserver.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squirrel.authserver.dao.entity.User;
import com.squirrel.authserver.dao.repository.UserRepository;
import com.squirrel.authserver.model.JsonEntity;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
		Objects.requireNonNull(username, "Username can not be empty!");
		Objects.requireNonNull(password, "Password can not be empty!");
		
		User user = userRepository.findByUsername(username);
		if (Objects.isNull(user) || !Objects.equals(user.getPassword(), password)) {
			
		}
		return null;
	}
}
