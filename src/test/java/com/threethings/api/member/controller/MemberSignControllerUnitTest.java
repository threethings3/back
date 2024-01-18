package com.threethings.api.member.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threethings.api.member.domain.Provider;
import com.threethings.api.member.dto.SignInRequest;
import com.threethings.api.member.dto.SignUpRequest;
import com.threethings.api.member.factory.dto.SignResponseFactory;
import com.threethings.api.member.factory.dto.SignUpRequestFactory;
import com.threethings.api.member.service.SignService;

@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
class MemberSignControllerUnitTest {
	@InjectMocks
	MemberSignController memberSignController;

	@Mock
	SignService signService;

	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void beforeEach(RestDocumentationContextProvider provider) {
		mockMvc = MockMvcBuilders.standaloneSetup(memberSignController)
			.apply(documentationConfiguration(provider).operationPreprocessors()
				.withRequestDefaults(prettyPrint())
				.withResponseDefaults(prettyPrint()))
			.build();
	}

	@Test
	@DisplayName("회원 가입 테스트")
	void signUpTest() throws Exception {
		// given
		final String url = "/api/sign-up";
		final SignUpRequest req = SignUpRequestFactory.createSignUpRequest();
		String expectedLocation =
			"/api/member/profiles/" + URLEncoder.encode(req.getNickname(), StandardCharsets.UTF_8);
		given(signService.signUp(any())).willReturn(SignResponseFactory.createSignResponse());

		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(req)));

		// then
		resultActions.andExpect(status().isCreated())
			.andExpect(header().stringValues("Location", expectedLocation))
			.andExpect(jsonPath("$.result.data.memberResponse.profileImageId").value("1"))
			.andExpect(jsonPath("$.result.data.memberResponse.nickname").value("닉네임"))
			.andExpect(jsonPath("$.result.data.tokenResponse.accessToken").value("access"))
			.andExpect(jsonPath("$.result.data.tokenResponse.refreshToken").value("refresh"));
	}

	@Test
	@DisplayName("로그인 테스트")
	void signIn() throws Exception {
		// given
		final String url = "/api/sign-in";
		final SignInRequest req = new SignInRequest("12345678", Provider.NAVER);
		given(signService.signIn(any())).willReturn(SignResponseFactory.createSignResponse());

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
		);

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.result.data.tokenResponse.accessToken").value("access"))
			.andExpect(jsonPath("$.result.data.tokenResponse.refreshToken").value("refresh"));

	}
}
