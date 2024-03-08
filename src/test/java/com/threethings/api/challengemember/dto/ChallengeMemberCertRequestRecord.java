package com.threethings.api.challengemember.dto;

import com.threethings.api.challengemember.domain.Certification;

public record ChallengeMemberCertRequestRecord(
	Long challengeId,
	Certification certification
) {
}
