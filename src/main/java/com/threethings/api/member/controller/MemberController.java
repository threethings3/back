package com.threethings.api.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threethings.api.global.common.Response;
import com.threethings.api.global.exception.DomainException;
import com.threethings.api.member.exception.MemberExceptionType;
import com.threethings.api.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/nickname/{nickname}")
	public ResponseEntity<Response> checkNickname(@PathVariable String nickname) {
		if (!memberService.isNicknameDuplicate(nickname)) {
			return ResponseEntity.ok(Response.success());
		} else {
			throw new DomainException(MemberExceptionType.NICKNAME_IS_EXIST);
		}
	}
}
