package com.ssts.ssts.domain.series.dto;


import com.ssts.ssts.domain.series.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SeriesResponseDto {

    private Long id;

    private String title;

    private int daylogCount;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int voteCount;

    private Boolean voteResult;

    private int voteAgree;

    private int voteDisagree;

    private Boolean revoteResult;

    private int revoteAgree;

    private int revoteDisagree;

    private Series.SeriesStatus seriesStatus;

    private Boolean isPublic;

    private Boolean isEditable;

    private Boolean isActive;

}
