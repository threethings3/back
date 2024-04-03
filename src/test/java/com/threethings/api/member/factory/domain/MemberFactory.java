package com.threethings.api.member.factory.domain;

import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.Provider;

public class MemberFactory {
	public static Member createMember() {
		return Member.builder()
			.nickname("member")
			.provider(Provider.NAVER)
			.socialCode("12345678")
			.profileImageId(1L).build();
	}

	public static Member anotherMember() {
		return Member.builder()
			.nickname("member1")
			.provider(Provider.NAVER)
			.socialCode("-12345678")
			.profileImageId(2L).build();
	}
}
