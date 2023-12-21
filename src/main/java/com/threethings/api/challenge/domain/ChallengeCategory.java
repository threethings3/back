package com.threethings.api.challenge.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ChallengeCategory {
	EXERCISE("운동", 1),
	DIET("식습관", 2),
	MINDFULNESS("마음챙김", 3),
	PET_CARE("반려동물", 4),
	HOBBY("취미", 5),
	GROWTH("성장", 6);

	private final String name;
	private final int imageCode;
}
