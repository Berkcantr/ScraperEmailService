package com.example.demo.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

	
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String userName = extractUsername(token);
		return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		Date now = new Date(System.currentTimeMillis());
		Date until = new Date(System.currentTimeMillis() + 10 * 60 * 1000);  // change the time with the numbers here
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now).setExpiration(until)
				.signWith(SECRET_KEY).compact();
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();	
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	/* 
	 * 
	 * 
	 * Either kill token when user logs out, and/or clear sessionStorage.
	 * If you dont kill token, if they access it after log out, they can reuse it.
	 * token lasts 10 min long so should not be an issue but structure wise is beter to kill.
	 * 
	 * As for edit, use email in sessionStorage to sent the PUT request and it should stop
	 * any user from editing any other user in the           ------ DO FIRST VERY EASY
	 * 
	 * Start on the article DB table, how they are displayed on the homepage, 
	 * 
	 */
	
	
}
