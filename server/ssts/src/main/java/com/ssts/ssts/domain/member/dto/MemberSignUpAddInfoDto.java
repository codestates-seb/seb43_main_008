package com.ssts.ssts.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberSignUpAddInfoDto {
    @NotEmpty(message = "핸드폰 번호는 필수 항목입니다.")
    @Pattern(regexp = "\\d{11}", message = "전화번호는 11자 숫자로만 입력해주세요.")
    private String phone;

    @NotEmpty(message = "닉네임은 필수 항목입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2~10자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]*$", message = "닉네임은 특수문자를 포함하지 않는 알파벳, 숫자, 한글만 입력 가능합니다.")
    private String nickName;
}
