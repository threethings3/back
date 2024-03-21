package com.threethings.api.challengemember.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.factory.ChallengeFactory;
import com.threethings.api.member.domain.Member;
import com.threethings.api.member.factory.domain.MemberFactory;

class ChallengeMemberCertificationTest {

	@Test
	@DisplayName("챌린지 인증 생성 테스트")
	void createCertificationHistory() {
		//given
		Challenge challenge = ChallengeFactory.createChallenge();
		Member member = MemberFactory.createMember();
		ChallengeMember challengeMember = new ChallengeMember(challenge, member);
		Certification certification = Certification.PERFECT;
		//when
		ChallengeMemberCertification created = ChallengeMemberCertification.builder()
			.challengeMember(challengeMember)
			.certification(certification)
			.build();
		//then
		assertThat(created.getChallengeMember().getChallenge()).isEqualTo(challenge);
		assertThat(created.getChallengeMember().getMember()).isEqualTo(member);
		assertThat(created.getCertification()).isEqualTo(certification);
	}
}
