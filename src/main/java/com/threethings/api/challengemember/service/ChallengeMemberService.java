package com.threethings.api.challengemember.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challengemember.domain.ChallengeMember;
import com.threethings.api.challengemember.repository.ChallengeMemberRepository;

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
