package com.threethings.api.challenge.converter;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DaysOfWeekConverter implements AttributeConverter<List<DayOfWeek>, String> {

	@Override
	public String convertToDatabaseColumn(List<DayOfWeek> dayOfWeeks) {
		return dayOfWeeks.stream().map(day -> Integer.toString(day.getValue()))
			.collect(Collectors.joining(","));
	}

	@Override
	public List<DayOfWeek> convertToEntityAttribute(String dbData) {
		return Arrays.stream(dbData.split(","))
			.map(Integer::parseInt)
			.map(DayOfWeek::of)
			.toList();
	}
}
