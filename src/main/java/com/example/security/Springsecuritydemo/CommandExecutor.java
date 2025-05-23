package com.example.security.Springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.security.Springsecuritydemo.entity.UserInfo;
import com.example.security.Springsecuritydemo.repo.UserRepo;

import jakarta.annotation.PostConstruct;

@Component
public class CommandExecutor implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	void saveAdminUser() {

		UserInfo user = new UserInfo(1, "admin", "admin@falses.in", "Welcome@123", "admin");
		System.out.println("Employees inserted");

		// userRepo.save(user);
	}

	@Override
	public void run(String... args) throws Exception {
		//userRepo.save(new UserInfo(1, "admin", "admin@falses.in", "Welcome@123", "admin"));

	}

}
