package com.threethings.api.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.threethings.api.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerAdviceTest {
	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	private MockMvc mockMvc;

	@BeforeEach
	void init() {
	}
}
