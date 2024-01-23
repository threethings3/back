package com.threethings.api.challengemember.domain;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class CertificationHistory {
	private Certification certification;
	private LocalDate certificationTime;
}
