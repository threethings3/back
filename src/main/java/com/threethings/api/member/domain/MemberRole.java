package com.threethings.api.member.domain;

import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements GrantedAuthority {
	ROLE_NORMAL,
	ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
