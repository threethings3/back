package com.threethings.api.challenge.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.threethings.api.challenge.converter.ChallengeCategoryConverter;
import com.threethings.api.challenge.converter.DaysOfWeekConverter;
import com.threethings.api.challenge_member.domain.ChallengeMember;
import com.threethings.api.global.common.BaseEntity;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = ChallengeCategoryConverter.class)
	private ChallengeCategory challengeCategory;

	private String title;

	@Embedded
	private Status status;

	@Embedded
	private CertificationTime certificationTime;

	@Convert(converter = DaysOfWeekConverter.class)
	private List<DayOfWeek> cycleDays;

	private LocalDate beginChallengeDate;
	private LocalDate endChallengeDate;
	private Boolean isPublic;
	private Integer maxParticipants;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "challenge")
	private List<ChallengeMember> members = new ArrayList<>();

	@Builder
	public Challenge(ChallengeCategory challengeCategory, String title, Status status,
		CertificationTime certificationTime,
		List<DayOfWeek> cycleDays, Integer challengePeriodWeeks, Boolean isPublic, Integer maxParticipants) {
		this.challengeCategory = challengeCategory;
		this.title = title;
		this.status = status;
		this.certificationTime = certificationTime;
		this.cycleDays = cycleDays;
		this.beginChallengeDate = calculateBeginDateTime(cycleDays, certificationTime,
			SystemTimeProvider.getInstance());
		this.endChallengeDate = calculateEndDateTime(beginChallengeDate, challengePeriodWeeks, cycleDays);
		this.isPublic = isPublic;
		this.maxParticipants = maxParticipants;
	}

	private LocalDate calculateBeginDateTime(List<DayOfWeek> cycleDays, CertificationTime certificationTime,
		TimeProvider timeProvider) {
		LocalDate today = timeProvider.getDate();
		LocalTime currentTime = timeProvider.getTime();
		DayOfWeek todayDayOfWeek = today.getDayOfWeek();

		// 오늘이 포함되어 있고, 현재 시간이 챌린지 수행 시작 시간보다 이전일 때
		if (cycleDays.contains(todayDayOfWeek) && currentTime.isBefore(certificationTime.getStartTime())) {
			return today;
		}
		// 오늘을 제외한, 다음으로 가까운 요일 찾기
		for (DayOfWeek dayOfWeek : cycleDays) {
			if (dayOfWeek.getValue() > todayDayOfWeek.getValue()) {
				return today.with(TemporalAdjusters.next(dayOfWeek));
			}
		}

		// 주어진 요일 중 오늘 이후로 가장 가까운 요일이 없으면, 가장 첫 번째 요일로 설정
		return today.with(TemporalAdjusters.next(cycleDays.get(0)));
	}

	private LocalDate calculateEndDateTime(LocalDate beginChallenge, int challengePeriodWeeks,
		List<DayOfWeek> cycleDays) {
		int beginIndex = cycleDays.indexOf(beginChallenge.getDayOfWeek());
		int endIndex = (beginIndex - 1 + cycleDays.size()) % cycleDays.size();
		return beginChallenge.with(TemporalAdjusters.next(cycleDays.get(endIndex)))
			.plusWeeks(challengePeriodWeeks - 1);
	}

	public void addMember(ChallengeMember member) {
		if (members.size() < maxParticipants) {
			members.add(member);
		}
	}
}
