package com.threethings.api.member.advice;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threethings.api.global.GlobalExceptionHandler;
import com.threethings.api.member.controller.MemberSignController;
import com.threethings.api.member.dto.SignInRequest;
import com.threethings.api.member.exception.MemberErrorResult;
import com.threethings.api.member.exception.MemberException;
import com.threethings.api.member.factory.dto.SignInRequestFactory;
import com.threethings.api.member.service.SignService;

@ExtendWith(MockitoExtension.class)
public class MemberSignControllerAdviceTest {
	@InjectMocks
	MemberSignController memberSignController;

	@Mock
	SignService signService;

	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberSignController)
			.setControllerAdvice(new GlobalExceptionHandler())
			.build();
	}

	@Test
	@DisplayName("로그인 실패")
	void signInFailureExceptionTest() throws Exception {
		// given
		String url = "/api/sign-in";
		SignInRequest req = SignInRequestFactory.createSignInRequestFactory();
		given(signService.signIn(any())).willThrow(new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));

		// when, then
		mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(req)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.code").value(-1000))
			.andExpect(jsonPath("$.result.msg").value("Member Not Found"));
	}
}
