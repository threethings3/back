package com.threethings.api.config.token;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHandler {
	private static final String TYPE = "Bearer ";

	public String generateToken(Key key, Map<String, Object> privateClaims, long maxAgeSeconds) {
		Date now = new Date();
		return TYPE + Jwts.builder()
			.setHeaderParam("typ", "JWT")
			.setHeaderParam("alg", "HS256")
			.addClaims(privateClaims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + maxAgeSeconds * 1000L))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public Optional<Claims> parse(Key key, String token) {
		try {
			return Optional.of(Jwts.parserBuilder().setSigningKey(key).build()
				.parseClaimsJws(untype(token)).getBody());
		} catch (JwtException e) {
			return Optional.empty();
		}
	}

	private String untype(String token) {
		return token.substring(TYPE.length());
	}
}
