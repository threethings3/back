package com.threethings.api.challenge_member.domain;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class CertificationHistory {
	private Certification certification;
	private LocalDate certificationTime;
}
