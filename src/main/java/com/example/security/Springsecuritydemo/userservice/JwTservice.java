package com.example.security.Springsecuritydemo.userservice;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.security.Springsecuritydemo.entity.UserAuthRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwTservice {

	public String genrateToken(UserAuthRequest request) {
		Map<String, Object> cla = new HashMap<>();
		return createToken(request, cla);
	}

	public String createToken(UserAuthRequest request, Map<String, Object> cla) {

		return Jwts.builder().setSubject(request.getUsername()).setIssuedAt(new Date())
				.signWith(getKey()).compact();

	}

	private Key getKey() {

		byte[] Keybyte = Decoders.BASE64.decode("TEST");

		return Keys.hmacShaKeyFor(Keybyte);

	}

	public String getUserName(String token) {

		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();

	}

}
