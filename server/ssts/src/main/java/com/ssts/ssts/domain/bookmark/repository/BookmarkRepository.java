package com.ssts.ssts.domain.bookmark.repository;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByMemberId(Long memberId);
    void deleteByMember_IdAndSeries_Id(Long memberId, Long seriseId);
}
