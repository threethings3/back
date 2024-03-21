package com.threethings.api.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.threethings.api.challengemember.controller.ChallengeMemberCertController;

@WebMvcTest(controllers = {
	ChallengeMemberCertController.class
})
public abstract class BaseControllerUnitTest {
	@Autowired
	protected MockMvc mockMvc;
	protected GsonBuilder gsonBuilder = new GsonBuilder();
	protected Gson gson;
}
