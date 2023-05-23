package com.ssts.ssts.domain.badges.repository;

import com.ssts.ssts.domain.badges.entity.Badge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {


}
