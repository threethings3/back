package com.threethings.api.challengemember.converter;

import com.threethings.api.challengemember.domain.Certification;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ChallengeMemberCertificationConverter implements AttributeConverter<Certification, Integer> {
	@Override
	public Integer convertToDatabaseColumn(Certification certification) {
		return certification.getCode();
	}

	@Override
	public Certification convertToEntityAttribute(Integer integer) {
		for (Certification certification : Certification.values()) {
			if (certification.getCode() == integer) {
				return certification;
			}
		}
		throw new IllegalArgumentException("알 수 없는 코드 :" + integer);
	}
}
