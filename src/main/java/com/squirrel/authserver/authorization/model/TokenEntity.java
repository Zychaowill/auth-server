package com.squirrel.authserver.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenEntity {
	
	private Long userId;
	private String token;
}
