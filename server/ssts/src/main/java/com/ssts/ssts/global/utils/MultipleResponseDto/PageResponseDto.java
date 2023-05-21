package com.ssts.ssts.global.utils.MultipleResponseDto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponseDto<T> {

    private List<T> pagedata;
    private PageDto pageInfo;

    public PageResponseDto(List<T> data, Page pageInfo) {
        this.pagedata = data;
        this.pageInfo = new PageDto(pageInfo.getNumber() + 1,
                pageInfo.getSize(), pageInfo.getTotalElements(), pageInfo.getTotalPages()) {
        };
    }

}
