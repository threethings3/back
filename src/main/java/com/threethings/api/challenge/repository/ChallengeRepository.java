package com.threethings.api.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threethings.api.challenge.domain.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
