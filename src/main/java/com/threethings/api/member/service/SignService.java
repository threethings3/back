package com.threethings.api.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.config.token.TokenHelper;
import com.threethings.api.global.exception.DomainException;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.MemberRole;
import com.threethings.api.member.dto.SignInRequest;
import com.threethings.api.member.dto.SignResponse;
import com.threethings.api.member.dto.SignUpRequest;
import com.threethings.api.member.dto.TokenResponse;
import com.threethings.api.member.exception.MemberExceptionType;
import com.threethings.api.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignService {
	private final MemberRepository memberRepository;
	private final TokenHelper accessTokenHelper;
	private final TokenHelper refreshTokenHelper;

	@Transactional(readOnly = true)
	public SignResponse signIn(SignInRequest req) {
		Member member = memberRepository.findBySocialCodeAndProvider(req.getSocialCode(), req.getProvider())
			.orElseThrow(() -> new DomainException(MemberExceptionType.MEMBER_NOT_FOUND));
		return SignResponse.toDto(member, createTokens(createPrivateClaims(member)));
	}

	@Transactional
	public SignResponse signUp(SignUpRequest req) {
		Member member = SignUpRequest.toEntity(req);
		Member savedMember = memberRepository.save(member);
		return SignResponse.toDto(savedMember, createTokens(createPrivateClaims(savedMember)));
	}

	private TokenResponse createTokens(TokenHelper.PrivateClaims privateClaims) {
		return new TokenResponse(accessTokenHelper.createToken(privateClaims),
			refreshTokenHelper.createToken(privateClaims));
	}

	private TokenHelper.PrivateClaims createPrivateClaims(Member member) {
		return new TokenHelper.PrivateClaims(String.valueOf(member.getId()),
			member.getRoleSet().stream().map(MemberRole::getAuthority).toList());
	}
}
