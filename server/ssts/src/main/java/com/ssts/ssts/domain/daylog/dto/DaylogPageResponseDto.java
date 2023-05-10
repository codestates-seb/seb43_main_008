package com.ssts.ssts.domain.daylog.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@Setter
@Getter
public class DaylogPageResponseDto<T> {

    private List<T> data;

    private DaylogPageDto daylogPage;

    public DaylogPageResponseDto(List<T> data, Page pageInfo) {
        this.data = data;
        this.daylogPage = new DaylogPageDto(pageInfo.getNumber() + 1,
                pageInfo.getSize(), pageInfo.getTotalElements(), pageInfo.getTotalPages()) {
        };
    }





}
