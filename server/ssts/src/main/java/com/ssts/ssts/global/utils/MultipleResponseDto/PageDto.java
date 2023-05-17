package com.ssts.ssts.global.utils.MultipleResponseDto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageDto {


    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
