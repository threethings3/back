package com.threethings.api.member.dto;

import java.util.Set;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.Provider;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class SignUpRequest {
	private String socialCode;
	private Provider provider;
	private String nickname;
	private Long profileImageId;
	private Set<ChallengeCategory> favoriteChallengeCategories;

	public static Member toEntity(SignUpRequest req) {
		return Member.builder()
			.socialCode(req.getSocialCode())
			.provider(req.getProvider())
			.nickname(req.getNickname())
			.profileImageId(req.profileImageId)
			.favoriteChallengeCategories(req.getFavoriteChallengeCategories()).build();
	}
}
