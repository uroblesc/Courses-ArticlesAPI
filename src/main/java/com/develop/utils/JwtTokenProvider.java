package com.develop.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	private final SecretKey secretKey;
    private final long jwtExpirationInMs;
	
	public JwtTokenProvider(
            @Value("${app.jwt.secret-key}") String secret,
            @Value("${app.jwt.expiration-time}") long jwtExpirationInMs
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationInMs = jwtExpirationInMs;
    }
	
	public String generateToken(Authentication authentication, String role) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(secretKey)
                .compact();
    }
	
	public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
	
	public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
	
	public String extractRole(String token) {
	    return Jwts.parser()
	            .setSigningKey(secretKey)
	            .parseClaimsJws(token)
	            .getBody()
	            .get("role", String.class);
	}
}
