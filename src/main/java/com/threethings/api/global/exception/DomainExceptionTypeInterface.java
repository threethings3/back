package com.threethings.api.global.exception;

import org.springframework.http.HttpStatus;

public interface DomainExceptionTypeInterface {
	HttpStatus getHttpStatus();

	String getMessage();

	void printErrorLog();
}
