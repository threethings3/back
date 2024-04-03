package com.threethings.api.challenge.factory;

import java.time.LocalTime;
import java.util.List;

import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.challenge.domain.ChallengeProfile;
import com.threethings.api.challenge.domain.Goal;

public class ChallengeFactory {
	public static Challenge createChallenge() {
		return Challenge.builder()
			.challengeProfile(new ChallengeProfile(ChallengeCategory.GROWTH, 1L))
			.title("삼시 세끼 밥 먹기")
			.goal(new Goal("하루 세 끼 다 먹기", "하루 두 끼 다 먹기", "하루 한 끼 다 먹기"))
			.certificationTime(new CertificationTime(LocalTime.of(8, 0), LocalTime.of(22, 0)))
			.cycleDays(List.of(1, 2, 3, 4, 5, 6, 7))
			.durationWeeks(4)
			.isPublic(Boolean.TRUE)
			.maxParticipants(30).build();
	}
}
