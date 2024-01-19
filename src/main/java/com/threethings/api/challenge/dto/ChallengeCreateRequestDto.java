package com.threethings.api.challenge.dto;

import java.time.DayOfWeek;
import java.util.List;

import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.challenge.domain.Status;

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
	private Status status;
	private CertificationTime certificationTime;
	private Integer challengePeriodWeeks;
	private List<Integer> cycleDays;
	private Boolean isPublic;
	private Integer maxParticipants;

	public static Challenge toEntity(ChallengeCreateRequestDto req) {
		return Challenge.builder()
			.challengeCategory(req.challengeCategory)
			.title(req.getTitle())
			.status(req.getStatus())
			.certificationTime(req.getCertificationTime())
			.cycleDays(req.getDayOfWeekList(req.cycleDays))
			.challengePeriodWeeks(req.getChallengePeriodWeeks())
			.isPublic(req.getIsPublic())
			.maxParticipants(req.maxParticipants)
			.build();
	}

	private List<DayOfWeek> getDayOfWeekList(List<Integer> values) {
		values.sort(Integer::compare);
		return values.stream().map(DayOfWeek::of).toList();
	}
}
