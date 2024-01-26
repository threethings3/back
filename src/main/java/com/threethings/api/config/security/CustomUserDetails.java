package com.threethings.api.config.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

	private final String userId;
	private final Set<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAccountNonLocked() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEnabled() {
		throw new UnsupportedOperationException();
	}
}
