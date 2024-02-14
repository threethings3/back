package com.threethings.api.challenge.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.factory.ChallengeFactory;
import com.threethings.api.challenge.repository.ChallengeRepository;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTest {
	@InjectMocks
	ChallengeService challengeService;

	@Mock
	ChallengeRepository challengeRepository;

	@Test
	@DisplayName("챌린지 저장 테스트")
	void saveChallengeTest() {
		// given
		Challenge challenge = ChallengeFactory.createChallenge();

		// when
		challengeService.saveChallenge(challenge);

		// then
		then(challengeRepository).should(times(1)).save(any());
	}
}
