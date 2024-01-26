package com.threethings.api.docs;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threethings.api.challenge.domain.ChallengeCategory;
import com.threethings.api.global.common.DocsEnumType;
import com.threethings.api.member.domain.Provider;

@RestController
@RequestMapping("/test")
public class EnumController {
	@GetMapping("/enums")
	public ApiResponseDto<EnumDocs> findEnums() {

		// 문서화 하고 싶은 -> EnumDocs 클래스에 담긴 모든 Enum 값 생성
		Map<String, String> challengeCategory = getDocs(ChallengeCategory.values());
		Map<String, String> provider = getDocs(Provider.values());

		// 전부 담아서 반환 -> 테스트에서는 이걸 꺼내 해석하여 조각을 만들면 된다.
		return ApiResponseDto.of(EnumDocs.builder()
			.challengeCategory(challengeCategory)
			.provider(provider)
			.build());
	}

	private Map<String, String> getDocs(DocsEnumType[] enumTypes) {
		return Arrays.stream(enumTypes).collect(Collectors.toMap(DocsEnumType::getName, DocsEnumType::getDescription));
	}

}

