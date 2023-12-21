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

	@Transactional
	public void updateNickname(Long id, String newNickName) {
		Member member = findMember(id);
		if (isNicknameDuplicate(newNickName)) {
			throw new MemberException(MemberErrorResult.NICKNAME_IS_DUPLICATED);
		}
		member.updateNickname(newNickName);
	}

	public boolean isNicknameDuplicate(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}

	private Member findMember(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));
	}
}
