package com.ssts.ssts.domain.vote.repository;

import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findBySeries_Id(Long seriesId);
    //long countBySeriesId(Long seriesId);

    //entity를 객체단위로 선언시 사용 불가능
    //@Query("SELECT COUNT(v) FROM Vote v WHERE v.seriesId = :seriesId")
    //long countBySeriesIdQuery(@Param("series_id") Long seriesId);

    long countBySeries(Series series);
}
