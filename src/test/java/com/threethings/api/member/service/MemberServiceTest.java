package com.threethings.api.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.threethings.api.member.domain.Member;
import com.threethings.api.member.factory.domain.MemberFactory;
import com.threethings.api.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@InjectMocks
	MemberService memberService;

	@Mock
	MemberRepository memberRepository;

	@Test
	@DisplayName("닉네임 중복 체크")
	void checkNicknameDuplication() {
		// given
		given(memberRepository.existsByNickname(anyString())).willReturn(false);

		// when
		final boolean result = memberService.isNicknameDuplicate("닉네임");

		// then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("id로 회원 가져오기")
	void getMemberWithMemberId() {
		// given
		given(memberRepository.findById(anyLong())).willReturn(Optional.of(MemberFactory.createMember()));

		// when
		final Member member = memberService.findMember(1L);

		// then
		assertThat(member.getNickname()).isEqualTo("member");
	}
}
