package com.ssts.ssts.domain.bookmark.repository;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<Bookmark> findAllByMemberId(Long memberId, Pageable pageable);
    void deleteByMember_IdAndSeries_Id(Long memberId, Long seriseId);
    Boolean existsByMember_IdAndSeries_Id(Long memberId, Long seriseId);
}
