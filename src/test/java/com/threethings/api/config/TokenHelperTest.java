package com.threethings.api.config;

import static org.assertj.core.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.config.token.JwtHandler;
import com.threethings.api.config.token.TokenHelper;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

class TokenHelperTest {

	private static final String TEMP_KEY = Base64.encodeBase64String(
		"TEMP_KEY_FOR_JWT_HANDLER_TEST_THIS_IS_BIGGER_THAN_256_BITS".getBytes(StandardCharsets.UTF_8));
	TokenHelper tokenHelper;

	@BeforeEach
	void beforeEach() {
		tokenHelper = new TokenHelper(new JwtHandler(), createKey(TEMP_KEY), 1000L);
	}

	@Test
	@DisplayName("토큰 생성 및 파서 테스트")
	void createTokenAndParseTest() {
		// given
		String memberId = "1";
		List<String> roleTypes = List.of("NORMAL", "ADMIN");
		TokenHelper.PrivateClaims privateClaims = new TokenHelper.PrivateClaims(memberId, roleTypes);

		// when
		String token = tokenHelper.createToken(privateClaims);

		// then
		TokenHelper.PrivateClaims parsedPrivateClaims = tokenHelper.parse(token).orElseThrow(RuntimeException::new);
		assertThat(parsedPrivateClaims.getMemberId()).isEqualTo("1");
		assertThat(parsedPrivateClaims.getRoleTypes()).contains(roleTypes.get(0), roleTypes.get(1));
	}

	private Key createKey(String key) {
		byte[] secretKeyBytes = Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(secretKeyBytes);
	}
}
