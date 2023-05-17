package com.ssts.ssts.domain.series.dto;


import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class SeriesPageResponseDto<T> {

    private List<T> data;
    private SerisePageDto seriesPage;

    public SeriesPageResponseDto(List<T> data, Page pageInfo) {
        this.data = data;
        this.seriesPage = new SerisePageDto(pageInfo.getNumber() + 1,
                pageInfo.getSize(), pageInfo.getTotalElements(), pageInfo.getTotalPages()) {
        };
    }

}
