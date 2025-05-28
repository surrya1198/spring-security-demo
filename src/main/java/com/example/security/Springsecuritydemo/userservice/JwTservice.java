package com.example.security.Springsecuritydemo.userservice;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.security.Springsecuritydemo.entity.UserAuthRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwTservice {
	public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

	public String genrateToken(UserAuthRequest request) {
		Map<String, Object> cla = new HashMap<>();
		cla.put("exp", new Date(System.currentTimeMillis() + 1000 * 60 * 10));
		cla.put("sub", request.getEmail());
		cla.put("iat", new Date(System.currentTimeMillis()));

		return createToken(request, cla);
	}

	public String createToken(UserAuthRequest request, Map<String, Object> cla) {

		return Jwts.builder().claims(cla).signWith(getKey()).compact();
		// subject(SECRET).issuedAt(null).compact();

	}

	private Key getKey() {

//		KeyGenerator genrator = null;
//		try {
//			genrator = KeyGenerator.getInstance("HmacSHA256");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		SecretKey sceret = genrator.generateKey();
//
////		String key = Base64.getEncoder().encodeToString(sceret.getEncoded());
////
		byte[] keybyte = Decoders.BASE64.decode(SECRET);

		return Keys.hmacShaKeyFor(keybyte);

	}

	public String getUserName(String token) {

		return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();

	}

	public Claims getClaims(String token) {
		

			return Jwts.parser().setSigningKey(getKey()).build().parseSignedClaims(token).getPayload();
		

	}

	public boolean validateToken(String token) throws ExpiredJwtException {
		Date exp = getClaims(token).getExpiration();

		return exp.before(new Date(System.currentTimeMillis()));
	}

}
