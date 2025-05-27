package com.example.security.Springsecuritydemo.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.Springsecuritydemo.dto.UserDto;
import com.example.security.Springsecuritydemo.entity.UserAuthRequest;
import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;
import com.example.security.Springsecuritydemo.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(10);

	public UserDto addUser(UserDetailsInfos userInfo) {

		UserDto userDto = new UserDto();
		userDto.setEmail(userInfo.getEmail());
		userDto.setName(userInfo.getUsername());
		userDto.setRoles(userInfo.getRoles());
		userRepo.save(userInfo);
		return userDto;
	}

	public UserDto register(UserAuthRequest authRequest) {

		UserDto userDto = new UserDto();
		UserDetailsInfos user = new UserDetailsInfos();
		user.setEmail(authRequest.getEmail());
		user.setPassword(passEncoder.encode(authRequest.getPassword()));
		user.setUsername(authRequest.getUsername());
		userRepo.save(user);
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getUsername());

		return userDto;
	}

}
