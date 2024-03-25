package com.threethings.api.init;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.challenge.domain.ChallengeProfile;
import com.threethings.api.challenge.domain.Goal;
import com.threethings.api.challenge.repository.ChallengeRepository;

@Component
public class ChallengeTestInitDB {
	@Autowired
	ChallengeRepository challengeRepository;

	public void initDB() {
		initTestChallenge();
	}

	private void initTestChallenge() {
		Challenge challengeTestData1 = Challenge.builder()
			.challengeProfile(new ChallengeProfile(ChallengeCategory.GROWTH, 1L))
			.title("삼시 세끼 밥 먹기")
			.goal(new Goal("하루 세 끼 다 먹기", "하루 두 끼 다 먹기", "하루 한 끼 다 먹기"))
			.certificationTime(new CertificationTime(LocalTime.of(8, 0), LocalTime.of(22, 0)))
			.cycleDays(List.of(1, 2, 3, 4, 5, 6, 7))
			.challengePeriodWeeks(4)
			.isPublic(Boolean.TRUE)
			.maxParticipants(30).build();

		Challenge challengeTestData2 = Challenge.builder()
			.challengeProfile(new ChallengeProfile(ChallengeCategory.GROWTH, 1L))
			.title("공부하기")
			.goal(new Goal("3시간 공부하기", "2시간 공부하기", "1시간 공부하기"))
			.certificationTime(new CertificationTime(LocalTime.of(8, 0), LocalTime.of(22, 0)))
			.cycleDays(List.of(1, 3, 5, 6, 7))
			.challengePeriodWeeks(3)
			.isPublic(Boolean.TRUE)
			.maxParticipants(20).build();

		challengeRepository.saveAll(List.of(challengeTestData1, challengeTestData2));

		System.out.println("123");
	}
}
