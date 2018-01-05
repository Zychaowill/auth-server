package com.itjangz.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class AuthServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
}
