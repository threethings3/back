package com.threethings.api.config.token;

import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenHelper {

	private final JwtHandler jwtHandler;
	private final Key key;
	private final long maxAgeSeconds;

	private static final String SEP = ",";
	private static final String ROLE_TYPES = "ROLE_TYPES";
	private static final String MEMBER_ID = "MEMBER_ID";

	public String createToken(PrivateClaims privateClaims) {
		return jwtHandler.createToken(key,
			Map.of(MEMBER_ID, privateClaims.getMemberId(),
				ROLE_TYPES, String.join(SEP, privateClaims.getRoleTypes())), maxAgeSeconds);
	}

	public Optional<PrivateClaims> parse(String token) {
		return jwtHandler.parse(key, token).map(this::convert);
	}

	private PrivateClaims convert(Claims claims) {
		return new PrivateClaims(claims.get(MEMBER_ID, String.class),
			Arrays.asList(claims.get(ROLE_TYPES, String.class).split(SEP)));
	}

	@Getter
	@AllArgsConstructor
	public static class PrivateClaims {
		private String memberId;
		private List<String> roleTypes;
	}
}
