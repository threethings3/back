package com.threethings.api.docs;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnumDocs {
	private Map<String, String> challengeCategory;
	private Map<String, String> ageGroup;
	private Map<String, String> gender;
	private Map<String, String> provider;
}
