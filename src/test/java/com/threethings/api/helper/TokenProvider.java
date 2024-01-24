package com.threethings.api.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.threethings.api.member.factory.dto.SignInRequestFactory;
import com.threethings.api.member.service.SignService;

@Component
public class TokenProvider {
	private static SignService signService;

	@Autowired
	public TokenProvider(SignService signService) {
		TokenProvider.signService = signService;
	}

	public static String getValidAccessToken() {
		return signService.signIn(SignInRequestFactory.createSignInRequest()).getTokenResponse().getAccessToken();
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
