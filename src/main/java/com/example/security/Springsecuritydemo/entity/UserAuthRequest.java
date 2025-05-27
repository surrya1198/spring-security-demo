package com.example.security.Springsecuritydemo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest {
	
	@Id
	private Long id;
	private String username;
	private String password;
	private String email;
}
