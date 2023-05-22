package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFeedResponse extends FeedResponse {

    private boolean followedMember;

    public static MemberFeedResponse of(String nickName,
                                        String image,
                                        String introduce,
                                        boolean isFollowed) {

        MemberFeedResponse memberFeedResponse = new MemberFeedResponse();

        memberFeedResponse.setNickName(nickName);
        memberFeedResponse.setImage(image);
        memberFeedResponse.setIntroduce(introduce);
        memberFeedResponse.setFollowedMember(isFollowed);

        return memberFeedResponse;
    }


}
