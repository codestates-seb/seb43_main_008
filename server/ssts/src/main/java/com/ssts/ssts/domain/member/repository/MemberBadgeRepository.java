package com.ssts.ssts.domain.member.repository;

import com.ssts.ssts.domain.member.entity.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    Boolean existsByMember_IdAndBadgeId(Long memberId, Long badgeId);
    List<MemberBadge> findAllByMember_Id(Long memberId);

}
