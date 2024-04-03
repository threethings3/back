package com.threethings.api.member.dto;

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

	public static Member toEntity(SignUpRequest req) {
		return Member.builder()
			.socialCode(req.getSocialCode())
			.provider(req.getProvider())
			.nickname(req.getNickname())
			.profileImageId(req.profileImageId).build();
	}
}
