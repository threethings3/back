package com.threethings.api.challenge.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CertificationTimeTest {

	private static Clock fixedClock;

	@BeforeAll
	static void setup() {
		fixedClock = Clock.fixed(ZonedDateTime.of(2024, 1, 17, 10, 0, 0, 0, ZoneId.of("Asia/Seoul")).toInstant(),
			ZoneId.of("Asia/Seoul"));
	}

	@Test
	@DisplayName("현재 시간이 인증 시간에 포함")
	void canCertificationTest() {
		// given
		CertificationTime certificationTime = new CertificationTime(LocalTime.of(8, 0), LocalTime.of(11, 0));
		FixedTimeProvider fixedTimeProvider = new FixedTimeProvider(fixedClock);
		LocalTime now = fixedTimeProvider.getTime();

		// when
		final boolean result = certificationTime.canCertification(fixedTimeProvider);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("현재 시간이 인증 시간에 포함")
	void canNotCertificationTest() {
		// given
		CertificationTime certificationTime = new CertificationTime(LocalTime.of(11, 0), LocalTime.of(14, 0));
		FixedTimeProvider fixedTimeProvider = new FixedTimeProvider(fixedClock);
		LocalTime now = fixedTimeProvider.getTime();

		// when
		final boolean result = certificationTime.canCertification(fixedTimeProvider);

		// then
		assertThat(result).isFalse();
	}
}
