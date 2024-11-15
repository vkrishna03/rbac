package com.example.rbac.security.jwt;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final SecretKey key = Keys.hmacShaKeyFor(keyGenerator());	// secret key
	private final long jwtExpirationMs = 24 * 60 * 60 * 100;	// expiry time
	
	public String generateToken(String username) {
		
		return Jwts.builder()
				.issuer("me")
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key)
				.compact();
	}
	
public String generateTokenWithRoles(String username, List<Long> roleIds) {
		
		return Jwts.builder()
				.issuer("me")
				.subject(username)
				.issuedAt(new Date())
				.claim("roles", roleIds)
				.expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key)
				.compact();
	}
	
	public Claims getClaimsFromToken(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public boolean isTokenValid(String token) {
		return (!isTokenExpired(token));
	}
	
	public boolean isTokenValid(String token, String username) {
		String tokenUsername = getClaimsFromToken(token).getSubject();
		
		return ((tokenUsername.equals(username)) && !isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token) {
		return getClaimsFromToken(token).getExpiration().before(new Date());
	}
	
	public String extractUsername(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	
	public byte[] keyGenerator() {
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[32];
		random.nextBytes(key);
		return key;
	}

}
