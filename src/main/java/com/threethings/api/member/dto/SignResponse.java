package com.threethings.api.member.dto;

import com.threethings.api.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignResponse {
	private MemberResponse memberResponse;

	private TokenResponse tokenResponse;

	public static SignResponse toDto(Member member, TokenResponse tokenResponse) {
		return new SignResponse(MemberResponse.toDto(member), tokenResponse);
	}
}
