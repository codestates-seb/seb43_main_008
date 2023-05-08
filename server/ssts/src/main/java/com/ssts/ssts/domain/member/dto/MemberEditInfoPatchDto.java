package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEditInfoPatchDto {
    String nickName;
    String introduce;
    String image;
    //String password; //oauth로그인이라서 필요없다


}
