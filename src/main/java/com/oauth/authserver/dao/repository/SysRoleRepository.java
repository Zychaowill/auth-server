package com.oauth.authserver.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth.authserver.dao.entity.SysRole;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

}
