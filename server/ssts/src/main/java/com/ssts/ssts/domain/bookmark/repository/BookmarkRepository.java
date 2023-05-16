package com.ssts.ssts.domain.bookmark.repository;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
