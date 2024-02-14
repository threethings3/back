package com.threethings.api.member.factory.dto;

import com.threethings.api.member.domain.Provider;
import com.threethings.api.member.dto.SignInRequest;

public class SignInRequestFactory {
	public static SignInRequest createSignInRequest() {
		return new SignInRequest("12345678", Provider.NAVER);
	}
}
