package com.threethings.api.challenge.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.domain.ChallengeProfile;
import com.threethings.api.challenge.domain.Goal;
import com.threethings.api.challengemember.domain.ChallengeMember;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ChallengeDetailResponseDto {
	private ChallengeProfile challengeProfile;
	private String title;
	private Goal goal;
	private Integer durationWeeks;
	private LocalDate beginChallengeDate;
	private LocalDate endChallengeDate;
	private List<Integer> cycleDays;
	private CertificationTime certificationTime;
	private Integer maxParticipants;
	private List<Long> challengeMembersProfileImageId;
	private Boolean liked;

	public static ChallengeDetailResponseDto toDto(Challenge challenge, Long memberId) {
		return new ChallengeDetailResponseDto(challenge, memberId);
	}

	private ChallengeDetailResponseDto(Challenge challenge, Long memberId) {
		this.challengeProfile = challenge.getChallengeProfile();
		this.title = challenge.getTitle();
		this.goal = challenge.getGoal();
		this.durationWeeks = challenge.getDurationWeeks();
		this.beginChallengeDate = challenge.getBeginChallengeDate();
		this.endChallengeDate = challenge.getEndChallengeDate();
		this.cycleDays = convertDayOfWeekToInteger(challenge.getCycleDays());
		this.certificationTime = challenge.getCertificationTime();
		this.maxParticipants = challenge.getMaxParticipants();
		this.challengeMembersProfileImageId = getChallengeMembersProfileImageId(challenge.getMembers());
		this.liked = challenge.getFavoriteMembers().stream().anyMatch(member -> member.getId().equals(memberId));
	}

	private List<Integer> convertDayOfWeekToInteger(List<DayOfWeek> dayOfWeekList) {
		return dayOfWeekList.stream().map(DayOfWeek::getValue).toList();
	}

	private List<Long> getChallengeMembersProfileImageId(List<ChallengeMember> challengeMembers) {
		return challengeMembers.stream().map(cm -> cm.getMember().getProfileImageId()).toList();
	}
}
