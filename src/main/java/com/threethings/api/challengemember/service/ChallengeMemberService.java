package com.threethings.api.challengemember.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challengemember.domain.ChallengeMember;
import com.threethings.api.challengemember.domain.ChallengeMemberCertification;
import com.threethings.api.challengemember.dto.ChallengeMemberCertRequestDto;
import com.threethings.api.challengemember.exception.ChallengeMemberExceptionType;
import com.threethings.api.challengemember.repository.CertificationHistoryRepository;
import com.threethings.api.challengemember.repository.ChallengeMemberRepository;
import com.threethings.api.global.exception.DomainException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ChallengeMemberService {
	private final ChallengeMemberRepository challengeMemberRepository;
	private final CertificationHistoryRepository certificationHistoryRepository;

	@Transactional
	public void saveChallengeMember(ChallengeMember challengeMember) {
		challengeMemberRepository.save(challengeMember);
	}

	@Transactional
	public void saveChallengeMemberCert(Long memberId, ChallengeMemberCertRequestDto req) {
		ChallengeMember findChallengeMember = challengeMemberRepository.findByMemberIdAndChallengeId(memberId,
				req.getChallengeId())
			.orElseThrow(() -> new DomainException(ChallengeMemberExceptionType.NOT_FOUND_CHALLENGE_MEMBER));

		boolean exists = findChallengeMember.getCertificationHistories()
			.stream()
			.anyMatch(entity -> entity.getCertificationTime().equals(LocalDate.now()));
		//해당 챌린지 인증 내역 중복체크
		if (exists) {
			throw new DomainException(ChallengeMemberExceptionType.DUPLICATED_CHALLENGE_CERT);
		}
		ChallengeMemberCertification saveEntity = ChallengeMemberCertification.createCertificationHistory(
			findChallengeMember,
			req.getCertification());
		certificationHistoryRepository.save(saveEntity);
	}
}
