package com.threethings.api.member.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorResult {
	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, -1000, "Member Not Found"),
	NICKNAME_IS_EXIST(HttpStatus.CONFLICT, -1001, "Nickname Is Duplicated");

	private final HttpStatus httpStatus;
	private final int code;
	private final String message;
}
