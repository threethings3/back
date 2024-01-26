package com.threethings.api.global;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.threethings.api.config.security.CustomUserDetails;

public final class SecurityUtils {
	private SecurityUtils() {
	}

	public static Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
			return Long.parseLong(userDetails.getUserId());
		}
		return null;
	}
}
