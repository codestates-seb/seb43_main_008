package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFeedResponseDto {

    String nickName;
    String image;
    String introduce;

    public static MemberFeedResponseDto of(String nickName, String image, String introduce) {
        MemberFeedResponseDto memberFeedResponseDto = new MemberFeedResponseDto();

        memberFeedResponseDto.setNickName(nickName);
        memberFeedResponseDto.setImage(image);
        memberFeedResponseDto.setIntroduce(introduce);

        return memberFeedResponseDto;
    }
}
