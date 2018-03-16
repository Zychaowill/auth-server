package com.squirrel.authserver.authorization.manager;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import com.squirrel.authserver.authorization.model.TokenEntity;
import com.squirrel.authserver.constants.TokenConstants;

@Component
public class RedisTokenManager implements TokenManager {

	private RedisTemplate<Long, String> redis;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Autowired
	public void setRedis(RedisTemplate redis) {
		this.redis = redis;
		redis.setKeySerializer(new JdkSerializationRedisSerializer());
	}
	
	@Override
	public TokenEntity create(Long userId) {
		String token = UUID.randomUUID().toString().replace("-", "");
		TokenEntity entity = new TokenEntity(userId, token);
		redis.boundValueOps(userId).set(token, TokenConstants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
		return entity;
	}

	@Override
	public boolean check(TokenEntity entity) {
		if (entity == null)
			return false;
		
		String token = redis.boundValueOps(entity.getUserId()).get();
		if (Objects.isNull(token) || !Objects.equals(token, entity.getToken()))
			return false;
		redis.boundValueOps(entity.getUserId()).expire(TokenConstants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
		return false;
	}

	@Override
	public TokenEntity get(String authorication) {
		if (StringUtils.isEmpty(authorication))
			return null;
		
		String[] params = authorication.split("_");
		if (params.length != 2)
			return null;
		return new TokenEntity(Long.parseLong(params[0]), params[1]); 
	}

	@Override
	public void delete(Long userId) {
		redis.delete(userId);
	}

}
