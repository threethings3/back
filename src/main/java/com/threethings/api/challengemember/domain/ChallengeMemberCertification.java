package com.threethings.api.challengemember.domain;

import java.time.LocalDate;

import com.threethings.api.challengemember.converter.ChallengeMemberCertificationConverter;
import com.threethings.api.global.common.BaseEntity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeMemberCertification extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Convert(converter = ChallengeMemberCertificationConverter.class)
	private Certification certification;
	private LocalDate certificationTime;
	@ManyToOne(fetch = FetchType.LAZY)
	private ChallengeMember challengeMember;

	@Builder
	public ChallengeMemberCertification(ChallengeMember challengeMember, Certification certification) {
		this.certification = certification;
		this.certificationTime = LocalDate.now();
		addCertification(challengeMember);
	}
	private void addCertification(ChallengeMember challengeMember) {
		this.challengeMember = challengeMember;
		challengeMember.getCertificationHistories().add(this);
	}
}
