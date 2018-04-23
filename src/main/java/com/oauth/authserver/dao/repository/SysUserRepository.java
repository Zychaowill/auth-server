package com.oauth.authserver.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth.authserver.dao.entity.SysUser;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	Optional<SysUser> findByUsername(String username);
}
