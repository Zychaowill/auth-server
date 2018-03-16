package com.squirrel.authserver.authorization.manager;

import com.squirrel.authserver.authorization.model.TokenEntity;

public interface TokenManager {

	TokenEntity create(Long userId);
	
	boolean check(TokenEntity entity);
	
	TokenEntity get(String authorication);
	
	void delete(Long userId);
}
