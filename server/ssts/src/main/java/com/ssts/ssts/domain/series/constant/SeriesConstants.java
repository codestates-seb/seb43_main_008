package com.ssts.ssts.domain.series.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeriesConstants {

    FILE_DiRECTORY("series/series-image.png"),

    BUCKET_NAME("ssts-img");

    final String SeriesConstant;

}
