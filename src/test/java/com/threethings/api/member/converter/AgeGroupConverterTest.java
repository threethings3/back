package com.threethings.api.member.converter;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.member.domain.AgeGroup;

class AgeGroupConverterTest {
	AgeGroupConverter ageGroupConverter = new AgeGroupConverter();

	@Test
	@DisplayName("AgeGroup 컨버터 테스트 - Enum to DB")
	void convertToDatabaseColumnTest() {
		// given, when
		int code = ageGroupConverter.convertToDatabaseColumn(AgeGroup.TEENS);

		// then
		assertThat(code).isEqualTo(1);
	}

	@Test
	@DisplayName("AgeGroup 컨버터 테스트 - DB to Enum")
	void convertToEntityAttributeTest() {
		// given, when
		AgeGroup ageGroup = ageGroupConverter.convertToEntityAttribute(1);

		// then
		assertThat(ageGroup).isEqualTo(AgeGroup.TEENS);
	}
}
