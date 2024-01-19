package com.threethings.api.challenge_member.domain;

import java.util.ArrayList;
import java.util.List;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.member.domain.Member;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challenge_id")
	private Challenge challenge;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<CertificationHistory> certificationHistories = new ArrayList<>();

	public ChallengeMember(Challenge challenge, Member member) {
		this.challenge = challenge;
		this.member = member;
		challenge.addMember(this);
		member.getChallengeMemberList().add(this);
	}

}
