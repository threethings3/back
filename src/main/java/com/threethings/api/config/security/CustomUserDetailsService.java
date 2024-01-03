package com.threethings.api.config.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.threethings.api.config.token.TokenHelper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final TokenHelper accessTokenHelper;

	@Override
	public CustomUserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		return accessTokenHelper.parse(token)
			.map(this::convert)
			.orElse(null);
	}

	private CustomUserDetails convert(TokenHelper.PrivateClaims privateClaims) {
		return new CustomUserDetails(
			privateClaims.getMemberId(),
			privateClaims.getRoleTypes().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
		);
	}
}
