package com.example.security.Springsecuritydemo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="user_info",schema ="SECUREDB")
@Component
@NoArgsConstructor
public class UserDetailsInfos {
 
	public UserDetailsInfos(Long id, String username, String email, String password, String roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String username;
	private String email;
	private String password;
	private String roles;

//	public UserInfo(Long id, String name, String email, String password, String roles) {
//		this.id = id;
//		this.username = name;
//		this.email = email;
//		this.password = password;
//		this.roles = roles;
//	}
}
