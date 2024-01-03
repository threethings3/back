package com.threethings.api.member.factory;

import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.Provider;

public class MemberFactory {
	public static Member createMember() {
		return Member.builder()
			.nickname("member")
			.provider(Provider.NAVER)
			.socialCode("12345678").build();
	}

	public static Member createMember(String nickname, Provider provider, String socialCode) {
		return Member.builder()
			.nickname(nickname)
			.provider(provider)
			.socialCode(socialCode).build();
	}
}
