package com.threethings.api.member.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorResult {
	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Member Not Found"),
	NICKNAME_IS_EXIST(HttpStatus.CONFLICT, "Nickname Is Duplicated");

	private final HttpStatus httpStatus;
	private final String message;
}
