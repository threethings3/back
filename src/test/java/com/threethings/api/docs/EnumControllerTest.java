package com.threethings.api.docs;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.reflect.TypeToken;
import com.threethings.api.docs.utils.CustomResponseFieldsSnippet;
import com.threethings.api.docs.utils.RestDocsTest;

public class EnumControllerTest extends RestDocsTest {
	@Test
	public void enums() throws Exception {
		// 요청
		ResultActions result = this.mockMvc.perform(
			MockMvcRequestBuilders.get("/test/enums")
				.contentType(MediaType.APPLICATION_JSON)
		);

		// 결과값
		MvcResult mvcResult = result.andReturn();

		// 데이터 파싱
		EnumDocs enumDocs = getData(mvcResult);

		// 문서화 진행
		result.andExpect(status().isOk())
			.andDo(restDocs.document(
				customResponseFields("custom-response",
					beneathPath("data.challengeCategory").withSubsectionId("challengeCategory"), // (1)
					attributes(key("title").value("ChallengeCategory")),
					enumConvertFieldDescriptor((enumDocs.getChallengeCategory()))
				),
				customResponseFields("custom-response", beneathPath("data.provider").withSubsectionId("provider"),
					attributes(key("title").value("Provider")),
					enumConvertFieldDescriptor((enumDocs.getProvider()))
				)
			));
	}

	// 커스텀 템플릿 사용을 위한 함수
	public static CustomResponseFieldsSnippet customResponseFields(String type,
		PayloadSubsectionExtractor<?> subsectionExtractor,
		Map<String, Object> attributes, FieldDescriptor... descriptors) {
		return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors),
			attributes, true);
	}

	// Map으로 넘어온 enumValue를 fieldWithPath로 변경하여 리턴
	private static FieldDescriptor[] enumConvertFieldDescriptor(Map<String, String> enumValues) {
		return enumValues.entrySet().stream()
			.map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
			.toArray(FieldDescriptor[]::new);
	}

	// mvc result 데이터 파싱
	private EnumDocs getData(MvcResult result) throws IOException {
		ApiResponseDto<EnumDocs> apiResponseDto = gson.fromJson(result.getResponse().getContentAsString(),
			new TypeToken<ApiResponseDto<EnumDocs>>() {
			}.getType());

		return apiResponseDto.getData();
	}
}
