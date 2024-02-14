package com.threethings.api.challenge.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TimeProvider {
	LocalDate getDate();

	LocalTime getTime();
}
