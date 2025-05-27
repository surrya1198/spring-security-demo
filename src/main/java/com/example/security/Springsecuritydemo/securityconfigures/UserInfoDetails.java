package com.example.security.Springsecuritydemo.securityconfigures;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security.Springsecuritydemo.entity.UserDetailsInfos;

public class UserInfoDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	List<GrantedAuthority> grantAuth;

	public UserInfoDetails(UserDetailsInfos userInfo) {
//		super();
		this.username = userInfo.getUsername();
		this.password = userInfo.getPassword();
		this.grantAuth = List.of(userInfo.getRoles()).stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
