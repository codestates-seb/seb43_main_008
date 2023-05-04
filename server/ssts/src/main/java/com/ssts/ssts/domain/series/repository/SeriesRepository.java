package com.ssts.ssts.domain.series.repository;

import com.ssts.ssts.domain.series.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
}
