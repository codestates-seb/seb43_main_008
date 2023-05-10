package com.ssts.ssts.domain.daylog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DaylogPageDto {


    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
