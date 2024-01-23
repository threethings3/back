package com.threethings.api.challenge.dto;

import java.util.List;

import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.challenge.domain.Goal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeCreateRequestDto {
	private ChallengeCategory challengeCategory;
	private String title;
	private Goal goal;
	private CertificationTime certificationTime;
	private Integer challengePeriodWeeks;
	private List<Integer> cycleDays;
	private Boolean isPublic;
	private Integer maxParticipants;

	public static Challenge toEntity(ChallengeCreateRequestDto req) {
		return Challenge.builder()
			.challengeCategory(req.challengeCategory)
			.title(req.getTitle())
			.goal(req.getGoal())
			.certificationTime(req.getCertificationTime())
			.cycleDays(req.cycleDays)
			.challengePeriodWeeks(req.getChallengePeriodWeeks())
			.isPublic(req.getIsPublic())
			.maxParticipants(req.maxParticipants)
			.build();
	}

}
