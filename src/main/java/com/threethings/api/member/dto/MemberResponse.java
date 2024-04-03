package com.threethings.api.member.dto;

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

	public static MemberResponse toDto(Member member) {
		return MemberResponse.builder()
			.profileImageId(member.getProfileImageId())
			.nickname(member.getNickname())
			.build();
	}
}
