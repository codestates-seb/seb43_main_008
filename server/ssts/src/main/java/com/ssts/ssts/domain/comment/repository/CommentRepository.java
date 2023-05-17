package com.ssts.ssts.domain.comment.repository;

import com.ssts.ssts.domain.comment.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
