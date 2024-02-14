package com.threethings.api.member.advice;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.threethings.api.global.GlobalExceptionHandler;
import com.threethings.api.member.controller.MemberController;
import com.threethings.api.member.service.MemberService;

@ExtendWith(MockitoExtension.class)
public class MemberControllerAdviceTest {
	@InjectMocks
	MemberController memberController;

	@Mock
	MemberService memberService;

	MockMvc mockMvc;

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).setControllerAdvice(new GlobalExceptionHandler())
			.build();
	}

	@Test
	@DisplayName("닉네임 중복(이미 있는 닉네임) 테스트")
	void checkDuplicateNickNameFail() throws Exception {
		// given
		final String url = "/api/member/nickname/{nickname}";
		final String nickname = "existingNickname";
		given(memberService.isNicknameDuplicate(anyString())).willReturn(true);

		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url, nickname));

		// then
		resultActions.andExpect(status().isConflict());
	}

}
