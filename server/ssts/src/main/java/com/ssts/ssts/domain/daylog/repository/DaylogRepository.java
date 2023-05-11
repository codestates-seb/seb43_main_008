package com.ssts.ssts.domain.daylog.repository;


import com.ssts.ssts.domain.daylog.entity.Daylog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DaylogRepository extends JpaRepository<Daylog, Long> {
    Page<Daylog> findBySeries_id(Long SeriesId, Pageable pageable);

}
