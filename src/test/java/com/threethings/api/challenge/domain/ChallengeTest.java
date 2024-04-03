package com.threethings.api.challenge.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * 챌린지 엔티티 테스트
 * 챌린지 시작 시간, 끝나는 시간 테스트를 위해 2024-01-17 10시로 시간 설정(수요일)
 */

class ChallengeTest {
	private static Clock fixedClock;

	@BeforeAll
	static void setup() {
		fixedClock = Clock.fixed(
			ZonedDateTime.of(2024, 1, 17, 10, 0, 0, 0,
				ZoneId.of("Asia/Seoul")).toInstant(),
			ZoneId.of("Asia/Seoul"));
	}

	@ParameterizedTest
	@DisplayName("챌린지 시작 시간 구하기")
	@MethodSource("provideArgumentsForChallengeStartDaysTest")
	void calculateBeginDateTimeTest(List<DayOfWeek> cycleDays, CertificationTime certificationTime,
		LocalDate expected) throws Exception {
		// given
		Challenge challenge = new Challenge();
		FixedTimeProvider fixedTimeProvider = new FixedTimeProvider(fixedClock);
		Method method = Challenge.class.getDeclaredMethod("calculateBeginDateTime", List.class,
			CertificationTime.class, TimeProvider.class);
		method.setAccessible(true);

		// when
		LocalDate result = (LocalDate)method.invoke(challenge, cycleDays, certificationTime, fixedTimeProvider);
		// then
		assertEquals(expected, result);
	}

	@ParameterizedTest
	@DisplayName("챌린지 마치는 시간 구하기")
	@MethodSource("provideArgumentsForChallengeEndDaysTest")
	void calculateEndDateTimeTest(LocalDate beginChallenge, int durationWeeks,
		List<DayOfWeek> cycleDays, LocalDate expected) throws Exception {
		// given, when
		Challenge challenge = new Challenge();
		Method method = Challenge.class.getDeclaredMethod("calculateEndDateTime",
			LocalDate.class, Integer.TYPE, List.class);
		method.setAccessible(true);

		LocalDate result = (LocalDate)method.invoke(challenge, beginChallenge, durationWeeks, cycleDays);
		// then
		assertEquals(expected, result);
	}

	private static Stream<Arguments> provideArgumentsForChallengeStartDaysTest() {
		return Stream.of(
			// 오늘 요일이 없고, 이번주에 남은 요일이 있음
			// = 해당 주에 남은 요일 중 가장 가까운 요일
			Arguments.of(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY),
				new CertificationTime(LocalTime.of(12, 0), LocalTime.of(14, 0)),
				LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.FRIDAY))),

			// 오늘 요일이 없고, 이번주에 남은 요일이 없음.
			// = 다음 주에 선택된 요일중 가장 가까운 요일
			Arguments.of(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
				new CertificationTime(LocalTime.of(12, 0), LocalTime.of(14, 0)),
				LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.MONDAY))),

			// 오늘 요일이 들어있고, 이번주에 남은 요일이 없으며 현재 시간이 챌린지 수행 시간보다 이전임.
			// = 오늘
			Arguments.of(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
				new CertificationTime(LocalTime.of(12, 0), LocalTime.of(14, 0)),
				LocalDate.now(fixedClock)),

			// 오늘 요일이 들어있고, 이번주에 남은 요일이 없으며 현재 시간이 챌린지 수행 시간보다 이후임.
			// = 다음 주에 선택된 요일중 가장 가까운 요일
			Arguments.of(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
				new CertificationTime(LocalTime.of(8, 0), LocalTime.of(10, 0)),
				LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.MONDAY))),

			// 오늘 요일이 들어있고, 이번주에 남은 요일이 있으며 현재 시간이 챌린지 수행 시간보다 이전임
			// = 오늘
			Arguments.of(List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
				new CertificationTime(LocalTime.of(12, 0), LocalTime.of(14, 0)),
				LocalDate.now(fixedClock)),

			// 오늘 요일이 들어있고, 이번주에 남은 요일이 있으며 현재 시간이 챌린지 수행 시간보다 이후임
			// = 해당 주에 오늘을 제외한 가장 가까운 요일
			Arguments.of(List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
				new CertificationTime(LocalTime.of(8, 0), LocalTime.of(10, 0)),
				LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.FRIDAY)))
		);
	}

	private static Stream<Arguments> provideArgumentsForChallengeEndDaysTest() {
		return Stream.of(
			// 오늘 부터 시작
			Arguments.of(LocalDate.now(fixedClock),
				3,
				List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
				LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).plusWeeks(2)
			),
			// 가장 가까운 요일부터 시작
			Arguments.of(LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.MONDAY)),
				4,
				List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
				LocalDate.now(fixedClock).with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).plusWeeks(3))
		);
	}
}
