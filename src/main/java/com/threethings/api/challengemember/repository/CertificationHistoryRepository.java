package com.threethings.api.challengemember.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threethings.api.challengemember.domain.ChallengeMemberCertification;

public interface CertificationHistoryRepository extends JpaRepository<ChallengeMemberCertification, Long> {
}
