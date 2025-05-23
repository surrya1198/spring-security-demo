package com.example.security.Springsecuritydemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserInfo {

	public UserInfo(int id, String name, String email, String password, String roles) {
		this.id = id;
		this.username = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String email;
	private String password;
	private String roles;
}
