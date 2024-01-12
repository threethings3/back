package com.threethings.api.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.threethings.api.config.token.TokenHelper;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.Provider;
import com.threethings.api.member.dto.SignInRequest;
import com.threethings.api.member.dto.SignResponse;
import com.threethings.api.member.dto.SignUpRequest;
import com.threethings.api.member.exception.MemberErrorResult;
import com.threethings.api.member.exception.MemberException;
import com.threethings.api.member.factory.domain.MemberFactory;
import com.threethings.api.member.factory.dto.SignInRequestFactory;
import com.threethings.api.member.factory.dto.SignUpRequestFactory;
import com.threethings.api.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class SignServiceTest {
	SignService signService;
	@Mock
	MemberRepository memberRepository;
	@Mock
	TokenHelper accessTokenHelper;
	@Mock
	TokenHelper refreshTokenHelper;

	@BeforeEach
	void init() {
		signService = new SignService(memberRepository, accessTokenHelper, refreshTokenHelper);
	}

	@Test
	@DisplayName("회원가입 테스트")
	void signUpTest() {
		// given
		Member member = MemberFactory.createMember();
		SignUpRequest req = SignUpRequestFactory.createSignUpRequest();
		given(memberRepository.save(any())).willReturn(member);
		given(accessTokenHelper.createToken(any())).willReturn("access");
		given(refreshTokenHelper.createToken(any())).willReturn("refresh");

		// when
		final SignResponse res = signService.signUp(req);

		// then
		then(memberRepository).should(times(1)).save(any());
		assertThat(res.getMemberResponse().getNickname()).isEqualTo(member.getNickname());
		assertThat(res.getTokenResponse().getAccessToken()).isEqualTo("access");
		assertThat(res.getTokenResponse().getRefreshToken()).isEqualTo("refresh");
	}

	@Test
	@DisplayName("로그인 테스트")
	void signInTest() {
		// given
		Member member = MemberFactory.createMember();
		SignInRequest req = SignInRequestFactory.createSignInRequestFactory();
		given(memberRepository.findBySocialCodeAndProvider(any(), any())).willReturn(Optional.of(member));
		given(accessTokenHelper.createToken(any())).willReturn("access");
		given(refreshTokenHelper.createToken(any())).willReturn("refresh");

		// when
		final SignResponse res = signService.signIn(req);

		// then
		assertThat(res.getMemberResponse().getNickname()).isEqualTo(member.getNickname());
		assertThat(res.getTokenResponse().getAccessToken()).isEqualTo("access");
		assertThat(res.getTokenResponse().getRefreshToken()).isEqualTo("refresh");
	}

	@Test
	@DisplayName("로그인 테스트 - 실패")
	void signInFail_NotExistMember() {
		// given
		given(memberRepository.findBySocialCodeAndProvider(any(), any())).willReturn(Optional.empty());

		// when
		final MemberException result = assertThrows(MemberException.class, () ->
			signService.signIn(new SignInRequest("notexists", Provider.NAVER)));

		// then
		assertThat(result.getErrorResult()).isEqualTo(MemberErrorResult.MEMBER_NOT_FOUND);
	}
}
