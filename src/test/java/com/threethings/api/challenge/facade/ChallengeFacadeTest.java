package com.threethings.api.challenge.facade;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;
import com.threethings.api.challenge.factory.ChallengeCreateRequestFactory;
import com.threethings.api.challenge.service.ChallengeService;
import com.threethings.api.challengemember.service.ChallengeMemberService;
import com.threethings.api.member.factory.domain.MemberFactory;
import com.threethings.api.member.service.MemberService;

@ExtendWith(MockitoExtension.class)
class ChallengeFacadeTest {
	@InjectMocks
	ChallengeFacade challengeFacade;
	@Mock
	ChallengeService challengeService;
	@Mock
	MemberService memberService;
	@Mock
	ChallengeMemberService challengeMemberService;

	@Test
	@DisplayName("챌린지 생성 (파사드 테스트)")
	void createChallengeFacadeTest() {
		// given
		final Long memberId = 1L;
		final ChallengeCreateRequestDto requestDto = ChallengeCreateRequestFactory.createChallengeCreateRequestDto();

		// when
		given(memberService.findMember(memberId)).willReturn(MemberFactory.createMember());
		challengeFacade.createChallenge(memberId, requestDto);

		// then
		then(challengeService).should(times(1)).saveChallenge(any());
		then(challengeMemberService).should(times(1)).saveChallengeMember(any());
	}
}
