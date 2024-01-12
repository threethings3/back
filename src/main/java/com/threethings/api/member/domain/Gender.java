package com.threethings.api.member.domain;

import com.threethings.api.global.common.DocsEnumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender implements DocsEnumType {
	MALE("M"),
	FEMALE("F");


	private final String genderCode;

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public String getDescription() {
		return this.genderCode;
	}
}
