package com.threethings.api.member.dto;

import com.threethings.api.member.domain.Provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
	private String socialCode;
	private Provider provider;
}
