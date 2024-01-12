package com.threethings.api.member.factory.domain;

import java.util.Set;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.member.domain.AgeGroup;
import com.threethings.api.member.domain.Gender;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.Provider;

public class MemberFactory {
	public static Member createMember() {
		return Member.builder()
			.nickname("member")
			.provider(Provider.NAVER)
			.socialCode("12345678")
			.ageGroup(AgeGroup.TEENS)
			.profileImageId(1L)
			.gender(Gender.MALE)
			.favoriteChallengeCategories(Set.of(ChallengeCategory.EXERCISE, ChallengeCategory.GROWTH)).build();
	}
}
