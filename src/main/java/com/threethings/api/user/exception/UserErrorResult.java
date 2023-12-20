package com.threethings.api.user.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserErrorResult {
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, -1000, "User Not Found");
	private final HttpStatus httpStatus;
	private final int code;
	private final String message;
}
