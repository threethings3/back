package com.threethings.api.challenge.converter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.challenge.domain.ChallengeCategory;

class ChallengeCategoryConverterTest {
	ChallengeCategoryConverter challengeCategoryConverter = new ChallengeCategoryConverter();

	@Test
	@DisplayName("엔티티 -> DB")
	void convertToDatabaseColumnTest() {
		// given
		final ChallengeCategory category = ChallengeCategory.GROWTH;

		// when
		final Integer result = challengeCategoryConverter.convertToDatabaseColumn(category);

		// then
		assertThat(result).isEqualTo(category.getCode());
	}

	@Test
	@DisplayName("DB -> 엔티티")
	void convertToEntityAttributeTest() {
		// given
		final Integer code = ChallengeCategory.EXERCISE.getCode();

		// when
		final ChallengeCategory category = challengeCategoryConverter.convertToEntityAttribute(code);

		// then
		assertThat(category).isEqualTo(ChallengeCategory.EXERCISE);
	}

	@Test
	@DisplayName("DB -> 엔티티 코드 값 에러")
	void convertToEntityAttributeFailTest() {
		// given
		final Integer code = -100;

		// when, then
		assertThrows(IllegalArgumentException.class, () ->
			challengeCategoryConverter.convertToEntityAttribute(code));
	}

}
