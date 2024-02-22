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

import com.google.gson.Gson;
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

	Gson gson = new Gson();

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
		SignInRequest req = SignInRequestFactory.createSignInRequest();
		given(signService.signIn(any())).willThrow(new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));

		// when, then
		mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(gson.toJson(req)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result.msg").value("Member Not Found"));
	}
}
