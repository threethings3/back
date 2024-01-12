package com.threethings.api.member.domain;

import com.threethings.api.global.common.DocsEnumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AgeGroup implements DocsEnumType {
	TEENS(1),
	TWENTIES(2),
	THIRTIES(3),
	FORTIES_AND_ABOVE(4);

	private final int code;

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public String getDescription() {
		return String.valueOf(this.code);
	}
}
