package com.threethings.api.challengemember.dto;

import com.threethings.api.challengemember.domain.Certification;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeMemberCertRequestDto {
	@NotNull(message = "required challengeId")
	private Long challengeId;
	@NotNull(message = "required certification")
	private Certification certification;
}
