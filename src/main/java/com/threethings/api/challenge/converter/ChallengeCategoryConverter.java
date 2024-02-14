package com.threethings.api.challenge.converter;

import com.threethings.api.challenge.domain.ChallengeCategory;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ChallengeCategoryConverter implements AttributeConverter<ChallengeCategory, Integer> {
	@Override
	public Integer convertToDatabaseColumn(ChallengeCategory challengeCategory) {
		return challengeCategory.getCode();
	}

	@Override
	public ChallengeCategory convertToEntityAttribute(Integer integer) {
		for (ChallengeCategory category : ChallengeCategory.values()) {
			if (category.getCode() == integer) {
				return category;
			}
		}
		throw new IllegalArgumentException("알 수 없는 코드 :" + integer);
	}
}
