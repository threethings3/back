package com.threethings.api.challenge.domain;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedTimeProvider implements TimeProvider {
	private Clock clock;

	@Override
	public LocalDate getDate() {
		return LocalDate.now(clock);
	}

	@Override
	public LocalTime getTime() {
		return LocalTime.now(clock);
	}
}
