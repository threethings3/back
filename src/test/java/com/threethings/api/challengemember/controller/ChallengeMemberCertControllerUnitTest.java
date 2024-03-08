package com.threethings.api.challengemember.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.threethings.api.challengemember.domain.Certification;
import com.threethings.api.challengemember.dto.ChallengeMemberCertRequestRecord;
import com.threethings.api.challengemember.exception.ChallengeMemberExceptionType;
import com.threethings.api.challengemember.service.ChallengeMemberService;
import com.threethings.api.config.LocalDateSerializer;
import com.threethings.api.config.LocalTimeSerializer;
import com.threethings.api.global.BaseControllerUnitTest;
import com.threethings.api.global.exception.DomainException;
import com.threethings.api.helper.WithMockCustomUser;

class ChallengeMemberCertControllerUnitTest extends BaseControllerUnitTest {
	@MockBean
	ChallengeMemberService challengeMemberService;
	@BeforeEach
	void init() {
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
		gson = gsonBuilder.setPrettyPrinting().create();
	}

	@Test
	@WithMockCustomUser
	@DisplayName("챌린지 인증하기 성공")
	void challenge_cert_success() throws Exception {
		//given
		var request = new ChallengeMemberCertRequestRecord(1L, Certification.PERFECT);
		final String url = "/api/challenge/cert";

		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(request)));

		resultActions.andExpect(status().isOk());
	}

	@Test
	@WithMockCustomUser
	@DisplayName("챌린지 인증하기 실패 - Bad Request")
	void challenge_cert_fail_badRequest() throws Exception {
		//given
		var request = new ChallengeMemberCertRequestRecord(null, Certification.PERFECT);
		final String url = "/api/challenge/cert";

		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(request)));

		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockCustomUser
	@DisplayName("챌린지 인증하기 실패 - Not found challenge member")
	void challenge_cert_fail_not_found_challenge_member() throws Exception {
		//given
		var request = new ChallengeMemberCertRequestRecord(1L, Certification.PERFECT);
		final String url = "/api/challenge/cert";
		doThrow(new DomainException(ChallengeMemberExceptionType.NOT_FOUND_CHALLENGE_MEMBER))
			.when(challengeMemberService)
			.saveChallengeMemberCert(anyLong(), any());

		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(request)));

		resultActions.andExpect(status().isNotFound());
	}

	@Test
	@WithMockCustomUser
	@DisplayName("챌린지 인증하기 실패 - duplicated cert")
	void challenge_cert_fail_duplicated_cert() throws Exception {
		//given
		var request = new ChallengeMemberCertRequestRecord(1L, Certification.PERFECT);
		final String url = "/api/challenge/cert";
		doThrow(new DomainException(ChallengeMemberExceptionType.DUPLICATED_CHALLENGE_CERT))
			.when(challengeMemberService)
			.saveChallengeMemberCert(anyLong(), any());

		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(request)));

		resultActions.andExpect(status().isBadRequest());
	}
}
