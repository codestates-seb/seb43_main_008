package com.ssts.ssts.domain.daylog.repository;


import com.ssts.ssts.domain.daylog.entity.Daylog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DaylogRepository extends JpaRepository<Daylog, Long> {
}
