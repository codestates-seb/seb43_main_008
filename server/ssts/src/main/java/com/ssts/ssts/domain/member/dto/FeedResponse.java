package com.ssts.ssts.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedResponse {

    protected String nickName;
    protected String image;
    protected String introduce;

    public static FeedResponse of(String nickName, String image, String introduce) {
        FeedResponse feedResponse = new FeedResponse();

        feedResponse.setNickName(nickName);
        feedResponse.setImage(image);
        feedResponse.setIntroduce(introduce);

        return feedResponse;
    }

}
