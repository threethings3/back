package com.threethings.api.config.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TokenConfig {
	private final JwtHandler jwtHandler;

	@Bean
	public TokenHelper accessTokenHelper(
		@Value("${jwt.key.access}") String key,
		@Value("${jwt.access-token-validity-in-seconds}") long maxAgeSeconds) {
		byte[] secretKeyBytes = Decoders.BASE64.decode(key);
		return new TokenHelper(jwtHandler, Keys.hmacShaKeyFor(secretKeyBytes), maxAgeSeconds);
	}
}
