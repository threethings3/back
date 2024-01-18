package com.threethings.api.member.converter;

import com.threethings.api.member.domain.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {
	@Override
	public String convertToDatabaseColumn(Gender gender) {
		return gender.getGenderCode();
	}

	@Override
	public Gender convertToEntityAttribute(String code) {
		for (Gender gender : Gender.values()) {
			if (gender.getGenderCode().equals(code)) {
				return gender;
			}
		}
		throw new IllegalArgumentException("알 수 없는 코드" + code);
	}
}
