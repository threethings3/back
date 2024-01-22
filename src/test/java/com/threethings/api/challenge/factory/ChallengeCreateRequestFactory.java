package com.threethings.api.challenge.factory;

import java.time.LocalTime;
import java.util.List;

import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.challenge.domain.Status;
import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;

public class ChallengeCreateRequestFactory {
	public static ChallengeCreateRequestDto createChallengeCreateRequestDto() {
		return new ChallengeCreateRequestDto(ChallengeCategory.GROWTH,
			"삼시 세끼 밥 먹기",
			new Status("하루 세 끼 다 먹기", "하루 두 끼 다 먹기", "하루 한 끼 다 먹기"),
			new CertificationTime(LocalTime.of(8, 0), LocalTime.of(22, 0)),
			4,
			List.of(1, 2, 3, 4, 5, 6, 7),
			Boolean.TRUE,
			30);
	}
}
