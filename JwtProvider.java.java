package com.jerry.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

	public String generateToken(Authentication auth) {
		// Build the JWT token
		String jwt = Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 86400000)) // 24 hours
				.claim("email", auth.getName())
				.signWith(key) // Sign with the secret key
				.compact(); // Compact the JWT to a string

		return jwt;
	}
	
	public String getEmailFromJwtToken(String jwt) {
		// Parse the JWT token and extract claims
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt) // Parse the JWT token
				.getBody();

		// Extract email from claims
		String email = String.valueOf(claims.get("email"));

		return email;
	}
}
