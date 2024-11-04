package com.example.rbac.utils;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
	
	private final SecretKey key = Jwts.SIG.HS256.key().build();	// secret key
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
	
	public Claims getClaimsFromToken(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public boolean isTokenValid(String token, String username) {
		String tokenUsername = getClaimsFromToken(token).getSubject();
		
		return ((tokenUsername.equals(username)) && !isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token) {
		return getClaimsFromToken(token).getExpiration().before(new Date());
	}

}
