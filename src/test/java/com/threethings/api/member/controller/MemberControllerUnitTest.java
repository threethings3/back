package com.threethings.api.member.controller;

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

import com.threethings.api.member.service.MemberService;

@ExtendWith(MockitoExtension.class)
public class MemberControllerUnitTest {
	@InjectMocks
	MemberController memberController;

	@Mock
	MemberService memberService;

	MockMvc mockMvc;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}

	@Test
	@DisplayName("닉네임 중복(없는 닉네임) 테스트")
	void checkDuplicateNickNameSuccess() throws Exception {
		// given
		final String url = "/api/member/nickname/{nickname}";
		final String nickname = "uniqueNickname";
		given(memberService.isNicknameDuplicate(anyString())).willReturn(false);

		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url, nickname));

		// then
		resultActions.andExpect(status().isOk());
	}
}
