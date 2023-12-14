package com.threethings.api.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserException extends RuntimeException {
	private final UserErrorResult errorResult;
}
