package com.threethings.api.challenge.controller;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.threethings.api.challenge.domain.CertificationTime;
import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.challenge.domain.Status;
import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;

public class ChallengeControllerUnitTest {

	@Test
	void test() throws JsonProcessingException {
		ChallengeCreateRequestDto req = new ChallengeCreateRequestDto(
			ChallengeCategory.GROWTH, "밥 잘 먹기", new Status("3번 먹기", "2번 먹기", "1번 먹기"),
			new CertificationTime(LocalTime.of(8, 0), LocalTime.of(20, 0)), 4,
			List.of(1, 2, 3, 4, 5, 6, 7, 8), Boolean.FALSE, 1);
	}
}
