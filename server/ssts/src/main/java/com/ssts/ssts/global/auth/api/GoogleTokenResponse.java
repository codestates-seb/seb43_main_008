package com.ssts.ssts.global.auth.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//FIXME lombok 클래스의 getter/setter, toString(), equals(), hashCode() 등의 메서드를 자동 생성
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//FIXME Jackson JSON에서 Snake Case로 작성된 속성 이름과 JAVA 클래스의 필드 이름 사이의 매핑을 지정한다.
@NoArgsConstructor
public class GoogleTokenResponse {
    private String accessToken;
    private Integer expiresIn;
    private String scope;
    private String tokenType;
    private String idToken;
}
