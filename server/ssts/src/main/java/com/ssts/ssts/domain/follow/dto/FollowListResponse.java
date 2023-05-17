package com.ssts.ssts.domain.follow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FollowListResponse {
    private String nickName;
    private String introduce;

    public static FollowListResponse of(String nickName, String introduce) {
        FollowListResponse followListResponse = new FollowListResponse();
        followListResponse.setNickName(nickName);
        followListResponse.setIntroduce(introduce);
        return followListResponse;
    }
}
