package com.threethings.api.challengemember.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threethings.api.challengemember.dto.ChallengeMemberCertRequestDto;
import com.threethings.api.challengemember.service.ChallengeMemberService;
import com.threethings.api.config.security.CustomUserDetails;
import com.threethings.api.global.common.Response;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenge/cert")
public class ChallengeMemberCertController {

	private final ChallengeMemberService challengeMemberService;

	@PostMapping
	public ResponseEntity<Response> certChallenge(
		@Valid @RequestBody ChallengeMemberCertRequestDto dto,
		@AuthenticationPrincipal CustomUserDetails userDetail
	) {
		challengeMemberService.saveChallengeMemberCert(Long.valueOf(userDetail.getUserId()), dto);
		return ResponseEntity.ok(Response.success());
	}
}
