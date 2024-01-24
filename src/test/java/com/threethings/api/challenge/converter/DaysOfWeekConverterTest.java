package com.threethings.api.challenge.converter;

import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DaysOfWeekConverterTest {
	DaysOfWeekConverter daysOfWeekConverter = new DaysOfWeekConverter();

	@Test
	@DisplayName("엔티티 -> DB")
	void convertToDatabaseColumnTest() {
		// given
		List<DayOfWeek> dayOfWeeks = List.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

		// when
		String column = daysOfWeekConverter.convertToDatabaseColumn(dayOfWeeks);

		// then
		assertThat(column).isEqualTo("1,5");
	}

	@Test
	@DisplayName("DB -> 엔티티")
	void convertToEntityAttributeTest() {
		// given
		String column = "1,5";

		// when
		List<DayOfWeek> result = daysOfWeekConverter.convertToEntityAttribute(column);

		// then
		assertThat(result.size()).isEqualTo(2);
	}
}
