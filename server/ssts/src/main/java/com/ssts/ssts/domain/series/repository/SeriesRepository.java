package com.ssts.ssts.domain.series.repository;

import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.series.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Daylog> findAllDaylogsById(Long seriesId, Pageable pageable);

    /* 멤버 서비스에서 멤버 아이디에 해당하는 시리즈 목록 출력 목적 */
    List<Series> findAllByMemberId(long memberId);
}
