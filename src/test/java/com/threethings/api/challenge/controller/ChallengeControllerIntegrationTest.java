package com.threethings.api.challenge.controller;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.challenge.dto.ChallengeCreateRequestDto;
import com.threethings.api.challenge.factory.ChallengeCreateRequestFactory;
import com.threethings.api.docs.utils.RestDocsTest;
import com.threethings.api.helper.TokenProvider;
import com.threethings.api.init.MemberTestInitDB;

@Transactional
public class ChallengeControllerIntegrationTest extends RestDocsTest {
	@Autowired
	MemberTestInitDB memberTestInitDB;

	@BeforeEach
	void initDB() {
		memberTestInitDB.initDB();
	}

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
		resultActions.andExpect(status().isOk())
			.andDo(
				restDocs.document(
					requestHeaders(
						headerWithName("Authorization").description("AccessToken")
					),
					requestFields(
						fieldWithPath("challengeProfile.challengeCategory")
							.description("link:common/ChallengeCategory.html[카테고리,role=\"popup\"]"),
						fieldWithPath("challengeProfile.categoryImageId").description("이미지 고유 넘버"),
						fieldWithPath("title").description("챌린지 제목"),
						fieldWithPath("goal").description("챌린지 목표"),
						fieldWithPath("goal.perfect").description("최고 목표"),
						fieldWithPath("goal.better").description("중간 목표"),
						fieldWithPath("goal.good").description("최저 목표"),
						fieldWithPath("certificationTime").description("인증 시간"),
						fieldWithPath("certificationTime.startTime").description("인증 시작 시간"),
						fieldWithPath("certificationTime.endTime").description("인증 종료 시간"),
						fieldWithPath("challengePeriodWeeks").description("챌린지 진행"),
						fieldWithPath("cycleDays").description("수행 요일 [1,2,3,4] 와 같이 정렬해야 함"),
						fieldWithPath("isPublic").description("공개 여부"),
						fieldWithPath("maxParticipants").description("최대 참가자 수")
					),
					responseFields(
						fieldWithPath("success").description("성공 여부"),
						fieldWithPath("code").description("결과 코드")
					)
				)
			);
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
