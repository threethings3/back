package com.threethings.api.challengemember.service;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.threethings.api.challengemember.domain.Certification;
import com.threethings.api.challengemember.domain.ChallengeMember;
import com.threethings.api.challengemember.domain.ChallengeMemberCertification;
import com.threethings.api.challengemember.dto.ChallengeMemberCertRequestDto;
import com.threethings.api.challengemember.exception.ChallengeMemberExceptionType;
import com.threethings.api.challengemember.factory.ChallengeMemberFactory;
import com.threethings.api.challengemember.repository.CertificationHistoryRepository;
import com.threethings.api.challengemember.repository.ChallengeMemberRepository;
import com.threethings.api.global.exception.DomainException;

@ExtendWith(MockitoExtension.class)
class ChallengeMemberServiceTest {
	@InjectMocks
	ChallengeMemberService challengeMemberService;
	@Mock
	ChallengeMemberRepository challengeMemberRepository;
	@Mock
	CertificationHistoryRepository certificationHistoryRepository;

	@Test
	@DisplayName("챌린지 멤버 등록 성공")
	void saveChallengeMember() {
		//given
		ChallengeMember challengeMember = ChallengeMemberFactory.createChallengeMember();
		//when
		challengeMemberService.saveChallengeMember(challengeMember);
		//then
		then(challengeMemberRepository).should(times(1)).save(any());
	}

	@Test
	@DisplayName("챌린지 인증 성공")
	void saveChallengeMemberCert() {
		//given
		Long memberId = 1L;
		var challengeMember = ChallengeMemberFactory.createChallengeMember();
		var req = new ChallengeMemberCertRequestDto(1L, Certification.PERFECT);
		given(challengeMemberRepository.findByMemberIdAndChallengeId(anyLong(), anyLong())).willReturn(
			Optional.of(challengeMember));
		//when
		challengeMemberService.saveChallengeMemberCert(memberId, req);
		//then
		then(challengeMemberRepository).should(times(1)).findByMemberIdAndChallengeId(anyLong(), anyLong());
		then(certificationHistoryRepository).should(times(1)).save(any());
	}

	@Test
	@DisplayName("챌린지 인증 실패 - Challenge Member Not found")
	void saveChallengeMemberCertFailNotFound() {
		//given
		Long memberId = 1L;
		var req = new ChallengeMemberCertRequestDto(1L, Certification.PERFECT);
		given(challengeMemberRepository.findByMemberIdAndChallengeId(anyLong(), anyLong())).willThrow(
			new DomainException(
				ChallengeMemberExceptionType.NOT_FOUND_CHALLENGE_MEMBER));
		//when
		try {
			challengeMemberService.saveChallengeMemberCert(memberId, req);
		} catch (DomainException e) {
			System.out.println(e.getDomainExceptionTypeInterface().getMessage());
		}
		//then
		then(challengeMemberRepository).should(times(1)).findByMemberIdAndChallengeId(anyLong(), anyLong());
		then(certificationHistoryRepository).should(never()).save(any());
	}

	@Test
	@DisplayName("챌린지 인증 실패 - Duplicate Cert")
	void saveChallengeMemberCertFailDuplicateCert() {
		//given
		Long memberId = 1L;
		var req = new ChallengeMemberCertRequestDto(1L, Certification.PERFECT);
		var challengeMember = ChallengeMemberFactory.createChallengeMember();
		challengeMember.getCertificationHistories()
			.add(ChallengeMemberCertification.builder()
				.challengeMember(challengeMember)
				.certification(req.getCertification())
				.build());
		given(challengeMemberRepository.findByMemberIdAndChallengeId(anyLong(), anyLong())).willReturn(
			Optional.of(challengeMember));
		//when
		try {
			challengeMemberService.saveChallengeMemberCert(memberId, req);
		} catch (DomainException e) {
			System.out.println(e.getDomainExceptionTypeInterface().getMessage());
		}
		//then
		then(challengeMemberRepository).should(times(1)).findByMemberIdAndChallengeId(anyLong(), anyLong());
		then(certificationHistoryRepository).should(never()).save(any());
	}
}
