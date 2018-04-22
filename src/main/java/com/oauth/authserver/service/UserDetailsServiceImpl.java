package com.oauth.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.oauth.authserver.dao.bo.UserBO;
import com.oauth.authserver.dao.entity.SysUser;
import com.oauth.authserver.dao.repository.SysUserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserRepository sysUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUserRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		return new UserBO(user);
	}

}
