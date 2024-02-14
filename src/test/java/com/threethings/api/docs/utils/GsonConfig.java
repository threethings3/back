package com.threethings.api.docs.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.threethings.api.config.LocalDateSerializer;
import com.threethings.api.config.LocalTimeSerializer;

@Configuration
public class GsonConfig {
	@Bean
	public Gson gson(LocalDateSerializer localDateSerializer, LocalTimeSerializer localTimeSerializer) {
		return new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, localDateSerializer)
			.registerTypeAdapter(LocalTime.class, localTimeSerializer)
			.create();
	}
}
