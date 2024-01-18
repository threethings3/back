package com.threethings.api.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.threethings.api.member.domain.Member;
import com.threethings.api.member.domain.Provider;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByNickname(String nickname);

	Optional<Member> findBySocialCodeAndProvider(String socialCode, Provider provider);

	@EntityGraph(attributePaths = "roleSet")
	@Query("select m from Member m where m.id= :id")
	Optional<Member> getMemberByIdWithRoles(@Param("id") Long id);

	boolean existsByNickname(@Param("nickname") String nickname);
}

