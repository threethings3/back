package com.threethings.api.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.threethings.api.config.token.TokenHelper;

@Component
public class TokenProvider {
	private static TokenHelper accessTokenHelper;

	@Autowired
	public TokenProvider(TokenHelper accessTokenHelper) {
		TokenProvider.accessTokenHelper = accessTokenHelper;
	}

	public static String getValidAccessToken() {
		String memberId = "1";
		List<String> roleTypes = List.of("NORMAL", "ADMIN");
		TokenHelper.PrivateClaims privateClaims = new TokenHelper.PrivateClaims(memberId, roleTypes);
		return accessTokenHelper.createToken(privateClaims);
	}

	public static String getIncorrectSignatureToken() {
		return "asdasd".repeat(10);
	}

	public static String getExpiredAccessToken() {
		return "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"
			+ ".eyJNRU1CRVJfSUQiOiIxIiwiUk9MRV9UWVBFUyI6IlJPTEVfTk9STUFMIiw"
			+ "iaWF0IjoxNzA1NTYxNjMwLCJleHAiOjE3MDU1NjM0MzB9"
			+ ".kjy-2CAQNrFv0FTtLtPtKrkq1eVBDT17ZgVLzzIdhds";
	}

}
