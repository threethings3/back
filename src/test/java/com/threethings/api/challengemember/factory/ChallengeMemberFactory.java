package com.threethings.api.challengemember.factory;

import com.threethings.api.challenge.factory.ChallengeFactory;
import com.threethings.api.challengemember.domain.ChallengeMember;
import com.threethings.api.member.factory.domain.MemberFactory;

public class ChallengeMemberFactory {
	public static ChallengeMember createChallengeMember() {
		return new ChallengeMember(ChallengeFactory.createChallenge(), MemberFactory.createMember());
	}
}
