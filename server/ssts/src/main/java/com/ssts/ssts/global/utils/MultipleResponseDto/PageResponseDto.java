package com.ssts.ssts.global.utils.MultipleResponseDto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponseDto<T> {

    private List<T> data;
    private PageDto seriesPage;

    public PageResponseDto(List<T> data, Page pageInfo) {
        this.data = data;
        this.seriesPage = new PageDto(pageInfo.getNumber() + 1,
                pageInfo.getSize(), pageInfo.getTotalElements(), pageInfo.getTotalPages()) {
        };
    }

}
