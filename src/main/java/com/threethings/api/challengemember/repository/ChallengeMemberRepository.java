package com.threethings.api.challengemember.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threethings.api.challengemember.domain.ChallengeMember;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {
}
