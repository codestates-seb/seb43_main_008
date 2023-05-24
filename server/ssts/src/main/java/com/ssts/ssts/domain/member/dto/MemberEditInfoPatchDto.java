package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberEditInfoPatchDto {

    private String nickName;
    private String introduce;
    private MultipartFile image;
}
