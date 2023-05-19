package com.ssts.ssts.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedResponse {

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

    @Setter
    @Getter
    public static class MemberFeedResponse extends FeedResponse{

        Boolean isFollowedMember;

        public static MemberFeedResponse of(
                String nickName,
                String image,
                String introduce,
                Boolean isFollowed)
        {
            MemberFeedResponse response=new MemberFeedResponse();

            response.setNickName(nickName);
            response.setImage(image);
            response.setIntroduce(introduce);
            response.setIsFollowedMember(isFollowed);

            return response;
        }
    }
}
