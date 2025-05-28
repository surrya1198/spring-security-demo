package com.example.security.Springsecuritydemo.userservice;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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

		return Jwts.builder().setSubject(request.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setClaims(cla).setExpiration(new Date(System.currentTimeMillis() * 1000 + 60 * 60 * 30))
				.signWith(getKey()).compact();

	}

	private Key getKey() {

		KeyGenerator genrator = null;
		try {
			genrator = KeyGenerator.getInstance("HmacSHA256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SecretKey sceret = genrator.generateKey();

		String key = Base64.getEncoder().encodeToString(sceret.getEncoded());

		byte[] keybyte = Decoders.BASE64.decode(key);

		return Keys.hmacShaKeyFor(keybyte);

	}

	public String getUserName(String token) {

		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();

	}

}
