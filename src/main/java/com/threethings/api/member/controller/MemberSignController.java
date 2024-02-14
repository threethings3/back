package com.threethings.api.member.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.threethings.api.global.common.Response;
import com.threethings.api.member.dto.SignInRequest;
import com.threethings.api.member.dto.SignUpRequest;
import com.threethings.api.member.service.SignService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberSignController {
	private final SignService signService;
	private static final String MEMBER_CREATED_URI = "/api/member/profiles";

	@PostMapping("/sign-up")
	public ResponseEntity<Response> signUp(@Valid @RequestBody SignUpRequest req) {
		URI location = ServletUriComponentsBuilder.fromPath(MEMBER_CREATED_URI)
			.pathSegment(req.getNickname()).build().toUri();
		return ResponseEntity.created(location)
			.body(Response.success(signService.signUp(req)));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<Response> signIn(@Valid @RequestBody SignInRequest req) {
		return ResponseEntity.ok(Response.success(signService.signIn(req)));
	}

}
