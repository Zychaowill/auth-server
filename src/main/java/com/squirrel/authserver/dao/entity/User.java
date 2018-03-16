package com.squirrel.authserver.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_")
@Getter
@Setter
public class User {
	@Id
	@Column(name = "id_")
	private Long id;
	
	@Column(name = "username_")
	private String username;
	
	@Column(name = "password_")
	private String password;
	
	@Column(name = "nickname_")
	private String nickname;
	
}
