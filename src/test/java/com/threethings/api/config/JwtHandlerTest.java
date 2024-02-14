package com.threethings.api.config;

import static org.assertj.core.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.config.token.JwtHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

class JwtHandlerTest {
	JwtHandler jwtHandler = new JwtHandler();
	private static final String TEMP_KEY = Base64.encodeBase64String(
		"TEMP_KEY_FOR_JWT_HANDLER_TEST_THIS_IS_BIGGER_THAN_256_BITS".getBytes(StandardCharsets.UTF_8));

	private static final String INVALID_KEY = Base64.encodeBase64String(
		"INVALID_KEY_FOR_JWT_HANDLER_TEST_THIS_IS_BIGGER_THAN_256_BITS".getBytes(StandardCharsets.UTF_8));

	@Test
	@DisplayName("토큰 생성 테스트")
	void createTokenTest() {
		// given, when
		String token = createToken(createKey(TEMP_KEY), Map.of(), 60L);

		// then
		assertThat(token).contains("Bearer ");
	}

	@Test
	@DisplayName("토큰 파서 테스트")
	void parseTest() {
		//given
		String key = "key";
		String value = "value";
		String token = createToken(createKey(TEMP_KEY), Map.of(key, value), 60L);

		// when
		Claims claims = jwtHandler.parse(createKey(TEMP_KEY), token).orElseThrow(RuntimeException::new);

		// then
		assertThat(claims.get(key)).isEqualTo(value);
	}

	@Test
	@DisplayName("토큰 파서 실패 - 유효하지 않은 키")
	void parseByInvalidKeyTest() {
		// given
		String key = "key";
		String value = "value";
		String token = createToken(createKey(TEMP_KEY), Map.of(key, value), 60L);

		// when
		Optional<Claims> claims = jwtHandler.parse(createKey(INVALID_KEY), token);

		// then
		assertThat(claims).isEmpty();
	}

	@Test
	@DisplayName("토큰 파서 실패 - 만료된 토큰")
	void parseByExpiredTokenTest() {
		// given
		String key = "key";
		String token = createToken(createKey(TEMP_KEY), Map.of(), 0L);

		// when
		Optional<Claims> claims = jwtHandler.parse(createKey(TEMP_KEY), token);

		// then
		assertThat(claims).isEmpty();
	}

	private Key createKey(String key) {
		byte[] secretKeyBytes = Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(secretKeyBytes);
	}

	private String createToken(Key key, Map<String, Object> claims, long maxAgeSeconds) {
		return jwtHandler.createToken(key, claims, maxAgeSeconds);
	}
}
