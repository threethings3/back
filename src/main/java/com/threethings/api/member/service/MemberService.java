package com.threethings.api.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.member.domain.Member;
import com.threethings.api.member.exception.MemberErrorResult;
import com.threethings.api.member.exception.MemberException;
import com.threethings.api.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	public boolean isNicknameDuplicate(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}

	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));
	}
}
