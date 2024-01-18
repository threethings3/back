package com.threethings.api.member.factory.dto;

import java.util.Set;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.member.domain.AgeGroup;
import com.threethings.api.member.domain.Gender;
import com.threethings.api.member.domain.Provider;
import com.threethings.api.member.dto.SignUpRequest;

public class SignUpRequestFactory {
	public static SignUpRequest createSignUpRequest() {
		return new SignUpRequest("12345678", Provider.NAVER, "닉네임", 1L,
			AgeGroup.TEENS, Gender.MALE, Set.of(ChallengeCategory.EXERCISE, ChallengeCategory.GROWTH));
	}
}
