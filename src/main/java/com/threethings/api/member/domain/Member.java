package com.threethings.api.member.domain;

import java.util.HashSet;
import java.util.Set;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10, unique = true)
	private String nickname;

	private String socialCode;

	@ElementCollection(fetch = FetchType.LAZY)
	@Enumerated(EnumType.STRING)
	private Set<MemberRole> roleSet = new HashSet<>();

	@Enumerated(EnumType.STRING)
	private Provider provider;

	@ElementCollection(fetch = FetchType.LAZY)
	@Enumerated(EnumType.STRING)
	private Set<ChallengeCategory> favoriteCategory = new HashSet<>();

	private Long profileImageId;

	@Builder
	public Member(String nickname, String socialCode, Provider provider) {
		this.nickname = nickname;
		this.socialCode = socialCode;
		this.provider = provider;
		addRole(MemberRole.ROLE_NORMAL);
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}

	public void addRole(MemberRole role) {
		this.roleSet.add(role);
	}

	public void addFavoriteCategories(Set<ChallengeCategory> newCategories) {
		this.favoriteCategory.clear();
		this.favoriteCategory.addAll(newCategories);
	}

}
