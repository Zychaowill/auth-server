package com.oauth.authserver.configuration.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.oauth.authserver.dao.entity.SysUser;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${resource.id:spring-boot-application}")
	private String resourceId;
	@Value("${access_token.validity_period:3600}")
	private int accessTokenValiditySeconds = 3600;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.accessTokenConverter(accessTokenConverter());
		endpoints.tokenStore(tokenStore());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')");
		security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("normal-app")
				.authorizedGrantTypes("authorization_code", "implicit")
				.authorities("ROLE_CLINET")
				.scopes("read", "write")
				.resourceIds(resourceId)
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
			.and()
			.withClient("trusted-app")
				.authorizedGrantTypes("client_credentials", "password")
				.authorities("ROLE_TRUSTED_CLIENT")
				.scopes("read", "write")
				.resourceIds(resourceId)
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.secret("secret");
			
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter accessTokenConverter = new DefaultJwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("123");
		return accessTokenConverter;
	}
	
	private static class DefaultJwtAccessTokenConverter extends JwtAccessTokenConverter {
		
		@Override
		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			String userName = authentication.getUserAuthentication().getName();
			SysUser user = (SysUser) authentication.getUserAuthentication().getPrincipal();
			final Map<String, Object> additionalInformation = new HashMap<String, Object>(2);
			additionalInformation.put("userName", userName);
			additionalInformation.put("roles", user.getRoles());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
			
			OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
			return enhancedToken;
		}
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
