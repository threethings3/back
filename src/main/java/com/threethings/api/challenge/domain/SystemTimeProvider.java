package com.threethings.api.challenge.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class SystemTimeProvider implements TimeProvider {
	private static final SystemTimeProvider INSTANCE = new SystemTimeProvider();

	private SystemTimeProvider() {
	}

	public static SystemTimeProvider getInstance() {
		return INSTANCE;
	}

	@Override
	public LocalDate getDate() {
		return LocalDate.now();
	}

	@Override
	public LocalTime getTime() {
		return LocalTime.now();
	}
}
