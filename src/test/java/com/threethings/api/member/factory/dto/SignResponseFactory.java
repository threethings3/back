package com.threethings.api.member.factory.dto;

import com.threethings.api.member.dto.MemberResponse;
import com.threethings.api.member.dto.SignResponse;
import com.threethings.api.member.dto.TokenResponse;

public class SignResponseFactory {
	public static SignResponse createSignResponse() {
		return new SignResponse(new MemberResponse(1L, "닉네임"),
			new TokenResponse("access", "refresh"));
	}
}
