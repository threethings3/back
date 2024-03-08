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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeMemberCertification extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Convert(converter = ChallengeMemberCertificationConverter.class)
	private Certification certification;
	private LocalDate certificationTime;
	@ManyToOne(fetch = FetchType.LAZY)
	private ChallengeMember challengeMember;

	public static ChallengeMemberCertification createCertificationHistory(ChallengeMember challengeMember,
		Certification certification) {
		return ChallengeMemberCertification.builder()
			.challengeMember(challengeMember)
			.certification(certification)
			.certificationTime(LocalDate.now())
			.build();
	}
}
