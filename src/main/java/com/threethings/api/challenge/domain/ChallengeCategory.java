package com.threethings.api.challenge.domain;

import com.threethings.api.global.common.DocsEnumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ChallengeCategory implements DocsEnumType {
	EXERCISE(1, "운동", 1),
	DIET(2, "식습관", 2),
	MINDFULNESS(3, "마음챙김", 3),
	PET_CARE(4, "반려동물", 4),
	HOBBY(5, "취미", 5),
	GROWTH(6, "성장", 6);

	private final int code;
	private final String name;
	private final int imageId;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public String getDescription() {
		return name;
	}
}
