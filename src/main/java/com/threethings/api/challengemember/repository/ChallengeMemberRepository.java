package com.threethings.api.challengemember.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.threethings.api.challengemember.domain.ChallengeMember;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

	@Query("select cm "
		+ "from ChallengeMember cm "
		+ "where cm.member.id = :memberId "
		+ "and cm.challenge.id = :challengeId ")
	Optional<ChallengeMember> findByMemberIdAndChallengeId(Long memberId, Long challengeId);
}
