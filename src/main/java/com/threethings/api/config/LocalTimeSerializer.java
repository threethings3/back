package com.threethings.api.config;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
public class LocalTimeSerializer implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	@Override
	public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
		return new JsonPrimitive(FORMATTER.format(localTime));
	}

	@Override
	public LocalTime deserialize(JsonElement jsonElement, Type type,
		JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return LocalTime.parse(jsonElement.getAsString(), FORMATTER);
	}
}
