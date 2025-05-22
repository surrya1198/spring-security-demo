package com.example.security.Springsecuritydemo.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.Springsecuritydemo.dto.UserDto;
import com.example.security.Springsecuritydemo.entity.UserInfo;
import com.example.security.Springsecuritydemo.userservice.UserService;

@RestController
@RequestMapping("/public/user")
public class UserController {

	@Autowired
	UserService userservice;

	@PostMapping("/auth")
	public void authenticate() {

	}

	@PostMapping("/adduser")
	public ResponseEntity<UserDto> addUser(@RequestBody UserInfo user) {
		return ResponseEntity.ofNullable(userservice.addUser(user));

	}

}
