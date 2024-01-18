package com.threethings.api.member.converter;

import com.threethings.api.member.domain.Provider;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProviderConverter implements AttributeConverter<Provider, String> {
	@Override
	public String convertToDatabaseColumn(Provider provider) {
		return provider.getDescription();
	}

	@Override
	public Provider convertToEntityAttribute(String code) {
		for (Provider provider : Provider.values()) {
			if (provider.getDescription().equals(code)) {
				return provider;
			}
		}
		throw new IllegalArgumentException("알 수 없는 코드 " + code);
	}
}
