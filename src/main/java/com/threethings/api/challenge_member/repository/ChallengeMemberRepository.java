package com.threethings.api.challenge_member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threethings.api.challenge_member.domain.ChallengeMember;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {
}
