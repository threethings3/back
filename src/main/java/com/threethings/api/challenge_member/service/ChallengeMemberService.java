package com.threethings.api.challenge_member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challenge_member.domain.ChallengeMember;
import com.threethings.api.challenge_member.repository.ChallengeMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeMemberService {
	private final ChallengeMemberRepository challengeMemberRepository;

	@Transactional
	public void saveChallengeMember(ChallengeMember challengeMember) {
		challengeMemberRepository.save(challengeMember);
	}
}
