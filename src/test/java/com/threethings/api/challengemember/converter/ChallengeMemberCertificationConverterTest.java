package com.threethings.api.challengemember.converter;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.challengemember.domain.Certification;

class ChallengeMemberCertificationConverterTest {

	ChallengeMemberCertificationConverter converter = new ChallengeMemberCertificationConverter();

	@Test
	@DisplayName("챌린지 멤버 인증 converter - convertToDatabaseColumn")
	void convertToDatabaseColumnTest() {
		Integer code = converter.convertToDatabaseColumn(Certification.PERFECT);
		//then
		assertThat(code).isEqualTo(3);
	}

	@Test
	@DisplayName("챌린지 멤버 인증 converter - convertToEntityAttribute")
	void convertToEntityAttributeTest() {
		Certification certification = converter.convertToEntityAttribute(3);
		//then
		assertThat(certification).isEqualTo(Certification.PERFECT);
	}

	@Test
	@DisplayName("챌린지 멤버 인증 converter - 알 수 없는 코드값 예외 발생")
	void convertToEntityAttributeExceptionTest() {
		//given
		int code = 10;
		//when then
		assertThatThrownBy(() -> converter.convertToEntityAttribute(code))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("알 수 없는 코드 :" + code);
	}
}
