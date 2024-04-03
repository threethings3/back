package com.threethings.api.member.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.threethings.api.docs.utils.RestDocsTest;
import com.threethings.api.init.MemberTestInitDB;
import com.threethings.api.member.domain.Provider;
import com.threethings.api.member.dto.SignInRequest;
import com.threethings.api.member.dto.SignUpRequest;
import com.threethings.api.member.factory.dto.SignUpRequestFactory;

@Transactional
public class MemberSignControllerIntegrationTest extends RestDocsTest {
	@Autowired
	MemberTestInitDB memberTestInitDB;

	@BeforeEach
	void init() {
		memberTestInitDB.initDB();
	}

	@Test
	@DisplayName("회원 가입 테스트 - POST /api/sign-up")
	void signUp() throws Exception {
		// given
		final String url = "/api/sign-up";
		final SignUpRequest req = SignUpRequestFactory.createSignUpRequest();
		String expectedLocation =
			"/api/member/profiles/" + URLEncoder.encode(req.getNickname(), StandardCharsets.UTF_8);

		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(req)));

		// then
		resultActions.andExpect(status().isCreated())
			.andExpect(header().stringValues("Location", expectedLocation))
			.andExpect(jsonPath("$.result.data.memberResponse.profileImageId").value("1"))
			.andExpect(jsonPath("$.result.data.memberResponse.nickname").value("닉네임"))
			.andExpect(jsonPath("$.result.data.tokenResponse.accessToken").value(startsWith("Bearer ")))
			.andExpect(jsonPath("$.result.data.tokenResponse.refreshToken").value(startsWith("Bearer ")))
			.andDo(
				restDocs.document(
					requestFields(
						fieldWithPath("socialCode").description("OAuth2 ID 토큰"),
						fieldWithPath("provider")
							.description("link:common/Provider.html[OAuth2 리소스 서버,role=\"popup\"]"),
						fieldWithPath("nickname").description("닉네임"),
						fieldWithPath("profileImageId").description("프로필 이미지 ID")
					),
					responseHeaders(
						headerWithName(HttpHeaders.LOCATION).description("Location header")
					),
					responseFields(
						fieldWithPath("success").description("성공 여부"),
						fieldWithPath("result.data.memberResponse.profileImageId").description("프로필 ID"),
						fieldWithPath("result.data.memberResponse.nickname").description("닉네임"),
						fieldWithPath("result.data.tokenResponse.accessToken").description("Access token"),
						fieldWithPath("result.data.tokenResponse.refreshToken").description("Refresh token"))
				)
			);
	}

	@Test
	@DisplayName("로그인 테스트 - POST /api/sign-in")
	void signIn() throws Exception {
		// given
		final String url = "/api/sign-in";
		final SignInRequest req = new SignInRequest("12345678", Provider.NAVER);

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(req))
		);

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.result.data.tokenResponse.accessToken").value(startsWith("Bearer ")))
			.andExpect(jsonPath("$.result.data.tokenResponse.refreshToken").value(startsWith("Bearer ")))
			.andDo(
				restDocs.document(
					requestFields(
						fieldWithPath("socialCode").description("OAuth2 ID 토큰"),
						fieldWithPath("provider").description("OAuth2 리소스 서버 이름")
					),
					responseFields(
						fieldWithPath("success").description("성공 여부"),
						fieldWithPath("result.data.memberResponse.profileImageId").description("프로필 ID"),
						fieldWithPath("result.data.memberResponse.nickname").description("닉네임"),
						fieldWithPath("result.data.tokenResponse.accessToken").description("Access token"),
						fieldWithPath("result.data.tokenResponse.refreshToken").description("Refresh token"))
				)
			);
	}
}
