package com.threethings.api.member.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.threethings.api.member.domain.Member;
import com.threethings.api.member.factory.MemberFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@PersistenceContext
	EntityManager em;

	private void clear() {
		em.flush();
		em.clear();
	}

	@Test
	@DisplayName("멤버 등록")
	void registerMember() {
		// given
		Member member = MemberFactory.createMember();

		// when
		Member savedMember = memberRepository.save(member);

		// then
		assertThat(savedMember.getId()).isNotNull();
		assertThat(savedMember.getProvider()).isEqualTo(member.getProvider());
		assertThat(savedMember.getSocialCode()).isEqualTo(member.getSocialCode());
	}

	@Test
	@DisplayName("닉네임 수정")
	void updateNickNameTest() {
		// given
		String updateNickname = "updated";
		Member member = memberRepository.save(MemberFactory.createMember());
		clear();

		// when
		Member foundMember = memberRepository.findById(member.getId()).get();
		foundMember.updateNickname(updateNickname);
		clear();

		// then
		Member updatedMember = memberRepository.findById(member.getId()).get();
		assertThat(updatedMember.getNickname()).isEqualTo(updateNickname);
		assertThat(member.getNickname()).isNotEqualTo(updatedMember.getNickname());
	}

	@Test
	@DisplayName("닉네임으로 회원 검색")
	void findByNickname() {
		// given
		Member member = memberRepository.save(MemberFactory.createMember());
		clear();

		// when
		Member foundMember = memberRepository.findByNickname(member.getNickname()).get();

		// then
		assertThat(member.getNickname()).isEqualTo(foundMember.getNickname());
	}
}
