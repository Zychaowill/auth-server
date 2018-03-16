package com.squirrel.authserver.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.squirrel.authserver.dao.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
}
