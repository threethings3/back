package com.threethings.api.helper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.util.Assert;

import com.threethings.api.config.security.CustomUserDetails;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (String role : annotation.roles()) {
			Assert.isTrue(!role.startsWith("ROLE_"), () -> "roles cannot start with ROLE_ Got " + role);
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		CustomUserDetails principal = new CustomUserDetails(annotation.userId(), grantedAuthorities);
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password",
			principal.getAuthorities());
		context.setAuthentication(auth);
		return context;
	}
}
