package com.threethings.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TokenResponse {
	private String accessToken;
	private String refreshToken;
}
