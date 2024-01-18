package com.threethings.api.member.converter;

import com.threethings.api.member.domain.AgeGroup;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AgeGroupConverter implements AttributeConverter<AgeGroup, Integer> {
	@Override
	public Integer convertToDatabaseColumn(AgeGroup ageGroup) {
		return ageGroup.getCode();
	}

	@Override
	public AgeGroup convertToEntityAttribute(Integer integer) {
		for (AgeGroup age : AgeGroup.values()) {
			if (age.getCode() == integer) {
				return age;
			}
		}
		throw new IllegalArgumentException("알 수 없는 코드" + integer);
	}
}
