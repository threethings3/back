package com.threethings.api.global;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.threethings.api.global.common.Response;
import com.threethings.api.global.exception.DomainException;
import com.threethings.api.global.exception.DomainExceptionTypeInterface;

import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Generated
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Domain Exception 처리
	 */
	@ExceptionHandler({DomainException.class})
	public ResponseEntity<Response> handleDomainException(final DomainException exception) {
		DomainExceptionTypeInterface ex = exception.getDomainExceptionTypeInterface();
		ex.printErrorLog();
		return makeErrorResponseEntity(ex.getHttpStatus(), ex.getMessage());
	}

	/**
	 * Exception 처리 -> 서버 에러로
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Response> handleException(final Exception exception) {
		log.warn("Exception occur: ", exception);
		return makeErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
	}

	/**
	 * RuntimeException 처리
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
		HttpStatusCode statusCode, WebRequest request) {
		log.warn("RuntimeException occur: ", ex);
		return ResponseEntity.status(statusCode)
			.body(Response.failure(ex.getMessage()));
	}

	/**
	 * Validation Field Error msg: default Message
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(Response.failure(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
	}

	private ResponseEntity<Response> makeErrorResponseEntity(HttpStatusCode statusCode, String message) {
		return ResponseEntity.status(statusCode)
			.body(Response.failure(message));
	}
}
