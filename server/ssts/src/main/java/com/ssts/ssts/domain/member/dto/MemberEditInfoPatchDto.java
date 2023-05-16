package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MemberEditInfoPatchDto {
    String nickName;
    String introduce;
    private MultipartFile image;
    //String password; //oauth로그인이라서 필요없다


}
