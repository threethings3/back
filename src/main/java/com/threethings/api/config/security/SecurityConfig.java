package com.threethings.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService userDetailsService;

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(FormLoginConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(new JwtAuthenticationFilter(userDetailsService),
				UsernamePasswordAuthenticationFilter.class)
			.build();
	}
}
