package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEditInfoResponseDto {
    String Image;
    String nickName;
    String introduce;
    //String password;

    public static MemberEditInfoResponseDto of(String image, String nickName, String introduce) {
        MemberEditInfoResponseDto memberEditInfoResponseDto = new MemberEditInfoResponseDto();

        memberEditInfoResponseDto.setImage(image);
        memberEditInfoResponseDto.setNickName(nickName);
        memberEditInfoResponseDto.setIntroduce(introduce);

        return memberEditInfoResponseDto;
    }
}
