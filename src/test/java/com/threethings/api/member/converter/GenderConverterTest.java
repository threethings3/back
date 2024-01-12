package com.threethings.api.member.converter;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.member.domain.Gender;

class GenderConverterTest {
	GenderConverter genderConverter = new GenderConverter();

	@Test
	@DisplayName("Gender 컨버터 테스트 - Enum To DB")
	void convertToDatabaseColumnTest() {
		// given, when
		String data = genderConverter.convertToDatabaseColumn(Gender.MALE);

		// then
		assertThat(data).isEqualTo("M");
	}

	@Test
	@DisplayName("Gender 컨버터 테스트 - DB To Enum")
	void convertToEntityAttributesTest() {
		// given, when
		Gender gender = genderConverter.convertToEntityAttribute("M");

		// then
		assertThat(gender).isEqualTo(Gender.MALE);
	}
}
