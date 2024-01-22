package com.threethings.api.challenge.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;
import com.threethings.api.challenge.service.ChallengeService;
import com.threethings.api.challenge_member.domain.ChallengeMember;
import com.threethings.api.challenge_member.service.ChallengeMemberService;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeFacade {
	private final ChallengeService challengeService;
	private final MemberService memberService;
	private final ChallengeMemberService challengeMemberService;

	public void createChallenge(Long memberId, ChallengeCreateRequestDto req) {
		Member member = memberService.findMember(memberId);
		Challenge challenge = ChallengeCreateRequestDto.toEntity(req);
		ChallengeMember challengeMember = new ChallengeMember(challenge, member);
		challengeService.saveChallenge(challenge);
		challengeMemberService.saveChallengeMember(challengeMember);
	}
}
