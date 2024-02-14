package com.threethings.api.member.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.member.factory.domain.MemberFactory;

class MemberTest {

	@Test
	@DisplayName("권한 추가 테스트")
	void addRoleTest() {
		Member member = MemberFactory.createMember();
		int beforeRoleSize = member.getRoleSet().size();
		member.addRole(MemberRole.ROLE_ADMIN);
		int afterRoleSize = member.getRoleSet().size();
		assertThat(afterRoleSize).isGreaterThan(beforeRoleSize);
		assertThat(afterRoleSize).isEqualTo(2);
	}

	@Test
	@DisplayName("선호 챌린치 카테고리 추가 테스트")
	void addFavoriteChallengeCategoriesTest() {
		Member member = MemberFactory.createMember();
		Set<ChallengeCategory> beforeChallengeCategories = new HashSet<>(member.getFavoriteChallengeCategories());
		member.addFavoriteChallengeCategories(Set.of(ChallengeCategory.MINDFULNESS, ChallengeCategory.EXERCISE));
		Set<ChallengeCategory> afterChallengeCategories = new HashSet<>(member.getFavoriteChallengeCategories());
		assertThat(beforeChallengeCategories).isNotEqualTo(afterChallengeCategories);
	}
}
