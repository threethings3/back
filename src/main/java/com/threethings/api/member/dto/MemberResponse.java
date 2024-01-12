package com.threethings.api.member.dto;

import java.util.Set;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.member.domain.AgeGroup;
import com.threethings.api.member.domain.Gender;
import com.threethings.api.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MemberResponse {
	private Long profileImageId;
	private String nickname;
	private Set<ChallengeCategory> favoriteChallengeCategories;
	private AgeGroup ageGroup;
	private Gender gender;

	public static MemberResponse toDto(Member member) {
		return MemberResponse.builder()
			.profileImageId(member.getProfileImageId())
			.nickname(member.getNickname())
			.favoriteChallengeCategories(member.getFavoriteChallengeCategories())
			.ageGroup(member.getAgeGroup())
			.gender(member.getGender()).build();
	}
}
