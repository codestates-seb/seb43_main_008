package com.ssts.ssts.domain.member.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberConstants {

    ADMIN_EMAIL("@ssts.site"),
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),

    FILE_NAME("member/default.png"),

    BUCKET_NAME("ssts-img");

    private String constant;
}
