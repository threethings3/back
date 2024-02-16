package com.threethings.api.challenge.domain;

import com.threethings.api.challenge.converter.ChallengeCategoryConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeProfile {
	@Convert(converter = ChallengeCategoryConverter.class)
	private ChallengeCategory challengeCategory;
	private Long categoryImageId;
}
