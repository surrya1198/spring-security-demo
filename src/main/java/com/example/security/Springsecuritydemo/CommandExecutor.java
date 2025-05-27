package com.example.security.Springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;
import com.example.security.Springsecuritydemo.repo.UserRepo;

import lombok.Data;

@Component
@Data
public class CommandExecutor implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	void saveAdminUser() {

//		UserInfo user = new UserInfo(1L, "admin", "admin@falses.in", "Welcome@123", "admin");
//		System.out.println("Employees inserted");

		// userRepo.save(user);
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(10);
		UserDetailsInfos user = new UserDetailsInfos(1L, "test", "test@gmail.com", passEncoder.encode("test"), "admin");

		userRepo.save(user);

		System.out.print("user saved sucessfully");

	}

}
