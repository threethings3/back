package com.threethings.api.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;
import com.threethings.api.challenge.dto.ChallengeLikeRequestDto;
import com.threethings.api.challenge.facade.ChallengeFacade;
import com.threethings.api.global.SecurityUtils;
import com.threethings.api.global.common.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
public class ChallengeController {

	private final ChallengeFacade challengeFacade;

	@PostMapping
	public ResponseEntity<Response> createChallenge(@RequestBody ChallengeCreateRequestDto req) {
		Long memberId = SecurityUtils.getCurrentUserId();
		challengeFacade.createChallenge(memberId, req);
		return ResponseEntity.ok(Response.success());
	}

	@PostMapping("/like")
	public ResponseEntity<Response> likeChallenge(@RequestBody ChallengeLikeRequestDto req) {
		challengeFacade.likeChallenge(SecurityUtils.getCurrentUserId(), req);
		return ResponseEntity.ok(Response.success());
	}

}
