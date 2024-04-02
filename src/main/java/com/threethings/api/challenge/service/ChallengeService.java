package com.threethings.api.challenge.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.dto.ChallengeSummaryResponseDto;
import com.threethings.api.challenge.exception.ChallengeExceptionType;
import com.threethings.api.challenge.repository.ChallengeRepository;
import com.threethings.api.global.exception.DomainException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {
	private final ChallengeRepository challengeRepository;

	@Transactional
	public void saveChallenge(Challenge challenge) {
		challengeRepository.save(challenge);
	}

	public Challenge findChallenge(Long challengeId) {
		return challengeRepository.findById(challengeId)
			.orElseThrow(() -> new DomainException(ChallengeExceptionType.CHALLENGE_NOT_FOUND));
	}

	public Challenge findChallengeWithFavoriteMembers(Long challengeId) {
		return challengeRepository.findChallengeById(challengeId)
			.orElseThrow(() -> new DomainException(ChallengeExceptionType.CHALLENGE_NOT_FOUND));
	}

	public Page<ChallengeSummaryResponseDto> searchChallenge(String keyword, Long memberId, Pageable pageable) {
		return challengeRepository.findByKeywordAndEndDateAfterAndIsPublicTrueWithMembers(
			keyword, memberId, LocalDate.now(), pageable);
	}

	public List<String> suggestionTitle(String keyword) {
		return challengeRepository.findTitle(keyword);
	}

	public Challenge getChallengeDetail(Long id) {
		return challengeRepository.findByIdWithMembers(id)
			.orElseThrow(() -> new DomainException(ChallengeExceptionType.CHALLENGE_NOT_FOUND));
	}
}
