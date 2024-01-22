package com.threethings.api.challenge.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;
import com.threethings.api.challenge.factory.ChallengeCreateRequestFactory;
import com.threethings.api.docs.utils.RestDocsTest;
import com.threethings.api.helper.TokenProvider;

public class ChallengeControllerIntegrationTest extends RestDocsTest {

	@Test
	@DisplayName("챌린지 생성 테스트 - POST /api/challenge")
	void createChallengeTest() throws Exception {
		// given
		final String url = "/api/challenge";
		final ChallengeCreateRequestDto requestDto = ChallengeCreateRequestFactory.createChallengeCreateRequestDto();

		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.header("Authorization", TokenProvider.getValidAccessToken())
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(requestDto)));

		// then
		resultActions.andExpect(status().isOk());
	}

	@ParameterizedTest
	@MethodSource("provideInvalidAuthorizationValue")
	@DisplayName("챌린지 생성 실패 테스트")
	void createChallengeFailTest(String invalidAuthorizationValue) throws Exception {
		// given
		final String url = "/api/challenge";
		final ChallengeCreateRequestDto requestDto = ChallengeCreateRequestFactory.createChallengeCreateRequestDto();

		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.header("Authorization", invalidAuthorizationValue)
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(requestDto)));

		// then
		resultActions.andExpect(status().isUnauthorized());
	}

	private static Stream<Arguments> provideInvalidAuthorizationValue() {
		return Stream.of(
			Arguments.of(TokenProvider.getExpiredAccessToken()),
			Arguments.of(TokenProvider.getIncorrectSignatureToken())
		);
	}

}
