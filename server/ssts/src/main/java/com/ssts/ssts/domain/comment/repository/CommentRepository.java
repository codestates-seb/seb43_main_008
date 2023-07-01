package com.ssts.ssts.domain.comment.repository;

import com.ssts.ssts.domain.comment.Entity.Comment;
import com.ssts.ssts.domain.series.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    Page<Comment> findByMember_id(Long memberId, Pageable pageable);
//
//    Page<Comment> findBySeries_id(Long SeriesId, Pageable pageable);
}
