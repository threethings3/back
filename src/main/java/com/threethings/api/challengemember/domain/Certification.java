package com.threethings.api.challengemember.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Certification {
	PERFECT(3), BETTER(2), GOOD(1), FAIL(0);
	private final int code;
}
