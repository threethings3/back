package com.threethings.api.member.factory.dto;

import com.threethings.api.member.domain.Provider;
import com.threethings.api.member.dto.SignUpRequest;

public class SignUpRequestFactory {
	public static SignUpRequest createSignUpRequest() {
		return new SignUpRequest("12345678", Provider.NAVER, "닉네임", 1L);
	}
}
