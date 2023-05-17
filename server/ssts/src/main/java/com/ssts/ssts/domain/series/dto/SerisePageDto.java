package com.ssts.ssts.domain.series.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SerisePageDto {


    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
