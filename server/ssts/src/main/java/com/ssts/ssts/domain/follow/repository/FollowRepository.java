package com.ssts.ssts.domain.follow.repository;

import com.ssts.ssts.domain.follow.entity.Follow;
import com.ssts.ssts.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}