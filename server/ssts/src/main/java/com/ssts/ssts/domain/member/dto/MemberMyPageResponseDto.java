package com.ssts.ssts.domain.member.dto;

import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberMyPageResponseDto {

    String nickName;
    String image;
    String introduce;

    public static MemberMyPageResponseDto of(String nickName, String image, String introduce) {
        MemberMyPageResponseDto memberMyPageResponseDto = new MemberMyPageResponseDto();

        memberMyPageResponseDto.setNickName(nickName);
        memberMyPageResponseDto.setImage(image);
        memberMyPageResponseDto.setIntroduce(introduce);

        return memberMyPageResponseDto;
    }
}
