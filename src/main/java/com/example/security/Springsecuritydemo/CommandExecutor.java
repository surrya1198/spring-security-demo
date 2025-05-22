package com.example.security.Springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.security.Springsecuritydemo.entity.UserInfo;
import com.example.security.Springsecuritydemo.repo.UserRepo;

@Component
public class CommandExecutor implements CommandLineRunner {
	@Autowired
	UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {

		UserInfo defaultUser = new UserInfo();
		defaultUser.setEmail("admin@gmail.com");
		defaultUser.setName("admin");
		defaultUser.setPassword("admin");
		defaultUser.setRoles("admin");

		userRepo.save(defaultUser);
	}

}
