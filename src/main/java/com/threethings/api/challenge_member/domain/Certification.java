package com.threethings.api.challenge_member.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Certification {
	PERFECT(3), BETTER(2), GOOD(1), FAIL(0);
	private final int code;
}
