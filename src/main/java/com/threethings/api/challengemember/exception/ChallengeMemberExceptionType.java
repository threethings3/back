package com.threethings.api.challengemember.exception;

import org.springframework.http.HttpStatus;

import com.threethings.api.global.exception.DomainExceptionTypeInterface;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public enum ChallengeMemberExceptionType implements DomainExceptionTypeInterface {
	NOT_FOUND_CHALLENGE_MEMBER(HttpStatus.NOT_FOUND, "챌린지 멤버가 아닙니다."),
	DUPLICATED_CHALLENGE_CERT(HttpStatus.BAD_REQUEST, "이미 인증된 챌린지입니다.");

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
		log.warn("ChallengeMember domain Error ========> httpStatus: {}, message: {}", this.httpStatus, this.message);
	}
}
