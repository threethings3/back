package com.threethings.api.member.exception;

import org.springframework.http.HttpStatus;

import com.threethings.api.global.exception.DomainExceptionTypeInterface;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public enum MemberExceptionType implements DomainExceptionTypeInterface {

	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Member Not Found"),
	NICKNAME_IS_EXIST(HttpStatus.CONFLICT, "Nickname Is Duplicated");

	private final HttpStatus httpStatus;
	private final String message;

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void printErrorLog() {
		log.warn("MEMBER domain Error ========> httpStatus: {}, message: {}", this.httpStatus, this.message);
	}

}
