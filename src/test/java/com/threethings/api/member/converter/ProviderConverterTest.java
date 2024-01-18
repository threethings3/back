package com.threethings.api.member.converter;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.member.domain.Provider;

class ProviderConverterTest {
	ProviderConverter providerConverter = new ProviderConverter();

	@Test
	@DisplayName("Provider 컨버터 테스트 - Enum to DB")
	void convertToDatabaseColumnTest() {
		// given, when
		String code = providerConverter.convertToDatabaseColumn(Provider.NAVER);

		// then
		assertThat(code).isEqualTo("N");
	}

	@Test
	@DisplayName("Provider 컨버터 테스트 - DB to Enum")
	void convertToEntityAttribute() {
		// given, when
		Provider provider = providerConverter.convertToEntityAttribute("N");

		// then
		assertThat(provider).isEqualTo(Provider.NAVER);
	}
}
