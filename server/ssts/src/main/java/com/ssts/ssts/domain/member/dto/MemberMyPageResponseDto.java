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
    //List<String> badgeList;
    //뱃지 일부를 자랑하는지 모르겠네. 그럼 필요하고
    List<Series> seriesList;
    //지금은 시리즈 아이디를 뽑는것으로 정해놨지만 나중에 시리즈 조회할때 엔티티가 나오면 그거로 넣으면 된당.

    public static MemberMyPageResponseDto of(String nickName, String image, String introduce, List<Series> seriesList) {
        MemberMyPageResponseDto memberMyPageResponseDto = new MemberMyPageResponseDto();

        memberMyPageResponseDto.setNickName(nickName);
        memberMyPageResponseDto.setImage(image);
        memberMyPageResponseDto.setIntroduce(introduce);
        memberMyPageResponseDto.setSeriesList(seriesList);

        return memberMyPageResponseDto;
    }
}
