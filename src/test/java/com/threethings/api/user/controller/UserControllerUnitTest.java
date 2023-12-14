package com.threethings.api.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import com.threethings.api.user.service.UserService;

class UserControllerUnitTest {

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	MockMvc mockMvc;

	@BeforeEach
	void init() {
	}

	@Test
	void get() {
		// given
		// when
		// then

	}
}
