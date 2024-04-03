package com.threethings.api.challenge.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.threethings.api.challenge.domain.Challenge;
import com.threethings.api.challenge.dto.ChallengeSummaryResponseDto;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	@Query(value = """
			SELECT new com.threethings.api.challenge.dto.ChallengeSummaryResponseDto(
				c.id, c.challengeProfile, c.title,  c.durationWeeks,
				c.beginChallengeDate, c.endChallengeDate, c.certificationTime,
				SIZE(c.members),
			CASE WHEN EXISTS (SELECT 1 FROM c.favoriteMembers fm WHERE fm.id = :memberId) THEN true ELSE false END)
			FROM  Challenge c
			WHERE c.title LIKE '%' || :keyword || '%'
				AND c.endChallengeDate > :currentDate
				AND c.isPublic = true
			ORDER BY SIZE(c.members) desc, c.beginChallengeDate desc
		""")
	Page<ChallengeSummaryResponseDto> findByKeywordAndEndDateAfterAndIsPublicTrueWithMembers(
		@Param("keyword") String keyword,
		@Param("memberId") Long memberId,
		@Param("currentDate") LocalDate currentDate,
		Pageable pageable);

	@Query(value = """
		SELECT c.title
		FROM Challenge c
		WHERE c.title LIKE '%' || :keyword || '%' AND c.isPublic = true
		ORDER BY c.createdAt DESC LIMIT 10
		""")
	List<String> findTitle(@Param("keyword") String keyword);

	@Query(value = """
		SELECT c FROM Challenge c
		LEFT JOIN FETCH c.members cm
		LEFT JOIN FETCH c.favoriteMembers
		LEFT JOIN FETCH cm.member
		WHERE c.id = :id AND c.isPublic = true
		""")
	Optional<Challenge> findByIdWithMembers(@Param("id") Long id);

	@EntityGraph(attributePaths = {"favoriteMembers"})
	Optional<Challenge> findChallengeById(Long id);
}
