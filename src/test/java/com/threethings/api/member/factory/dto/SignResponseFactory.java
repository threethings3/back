package com.threethings.api.member.factory.dto;

import java.util.Set;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.member.dto.MemberResponse;
import com.threethings.api.member.dto.SignResponse;
import com.threethings.api.member.dto.TokenResponse;

public class SignResponseFactory {
	public static SignResponse createSignResponse() {
		return new SignResponse(new MemberResponse(1L, "닉네임",
			Set.of(ChallengeCategory.EXERCISE, ChallengeCategory.GROWTH)),
			new TokenResponse("access", "refresh"));
	}
}
