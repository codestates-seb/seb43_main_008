package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MemberEditInfoPatchDto {
    private String nickName;
    private String introduce;
    private MultipartFile image;
}
