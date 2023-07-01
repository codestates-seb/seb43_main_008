package com.ssts.ssts.domain.vote.repository;

import com.ssts.ssts.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
