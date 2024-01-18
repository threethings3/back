package com.threethings.api.member.domain;

import com.threethings.api.global.common.DocsEnumType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Provider implements DocsEnumType {
	NAVER("N"), KAKAO("K"), GOOGLE("G");

	private final String code;

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public String getDescription() {
		return this.code;
	}
}
