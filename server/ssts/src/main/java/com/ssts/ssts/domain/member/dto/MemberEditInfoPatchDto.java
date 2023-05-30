package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberEditInfoPatchDto {

    @Size(min = 2, max = 10, message = "닉네임은 2~10자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]*$", message = "닉네임은 특수문자를 포함하지 않는 알파벳, 숫자, 한글만 입력 가능합니다.")
    private String nickName;

    @Size(max = 100, message = "소개글은 최대 100자까지 입력 가능합니다.")
    private String introduce;


    private MultipartFile image;
}
