package com.threethings.api.challenge_member.factory;

import com.threethings.api.challenge.factory.ChallengeFactory;
import com.threethings.api.challenge_member.domain.ChallengeMember;
import com.threethings.api.member.factory.domain.MemberFactory;

public class ChallengeMemberFactory {
	public static ChallengeMember createChallengeMember() {
		return new ChallengeMember(ChallengeFactory.createChallenge(), MemberFactory.createMember());
	}
}
