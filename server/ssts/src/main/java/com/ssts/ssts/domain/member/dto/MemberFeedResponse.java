package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFeedResponse {

    String nickName;
    String image;
    String introduce;

    public static MemberFeedResponse of(String nickName, String image, String introduce) {
        MemberFeedResponse memberFeedResponse = new MemberFeedResponse();

        memberFeedResponse.setNickName(nickName);
        memberFeedResponse.setImage(image);
        memberFeedResponse.setIntroduce(introduce);

        return memberFeedResponse;
    }
}
