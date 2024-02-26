package com.threethings.api.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainException extends RuntimeException {
	private final DomainExceptionTypeInterface domainExceptionTypeInterface;
}
