package com.threethings.api.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
