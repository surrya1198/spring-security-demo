package com.example.security.Springsecuritydemo.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.Springsecuritydemo.dto.UserDto;
import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;
import com.example.security.Springsecuritydemo.userservice.UserService;

@RestController
@RequestMapping("/user")
public class NonPublicController {
	
	@Autowired
	UserService userservice;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDetailsInfos user) {
		return ResponseEntity.ofNullable(userservice.addUser(user));

	}
}
