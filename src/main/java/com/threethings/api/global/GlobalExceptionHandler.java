package com.threethings.api.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.threethings.api.global.common.Response;
import com.threethings.api.member.exception.MemberErrorResult;
import com.threethings.api.member.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({MemberException.class})
	public ResponseEntity<Response> handleRestApiException(final MemberException exception) {
		log.warn("MemberException occur: ", exception);
		return makeErrorResponseEntity(exception.getErrorResult());
	}

	private ResponseEntity<Response> makeErrorResponseEntity(final MemberErrorResult errorResult) {
		return ResponseEntity.status(errorResult.getHttpStatus())
			.body(Response.failure(errorResult.getCode(), errorResult.getMessage()));
	}
}
