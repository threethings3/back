package com.threethings.api.challengemember.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.factory.ChallengeFactory;
import com.threethings.api.challengemember.domain.ChallengeMember;
import com.threethings.api.config.JpaAuditingConfig;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.factory.domain.MemberFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@Import(JpaAuditingConfig.class)
class ChallengeMemberRepositoryTest {
	@Autowired
	ChallengeMemberRepository challengeMemberRepository;
	@PersistenceContext
	EntityManager em;

	@Test
	@DisplayName("memberId, ChallengeId로 ChallengeMember 조회")
	void findByMemberIdAndChallengeId() {
		//given
		Challenge challenge = ChallengeFactory.createChallenge();
		Member member = MemberFactory.createMember();
		ChallengeMember challengeMember = new ChallengeMember(challenge, member);
		em.persist(challenge);
		em.persist(member);
		em.persist(challengeMember);
		em.flush();
		em.clear();
		//when
		ChallengeMember findChallengeMember = challengeMemberRepository.findByMemberIdAndChallengeId(member.getId(),
			challenge.getId()).get();
		//then
		assertThat(findChallengeMember.getChallenge().getId()).isEqualTo(challenge.getId());
		assertThat(findChallengeMember.getMember().getId()).isEqualTo(member.getId());
	}
}
