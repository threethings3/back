package com.threethings.api.challenge.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {
	private final ChallengeRepository challengeRepository;

	@Transactional
	public void saveChallenge(Challenge challenge) {
		challengeRepository.save(challenge);
	}
}
