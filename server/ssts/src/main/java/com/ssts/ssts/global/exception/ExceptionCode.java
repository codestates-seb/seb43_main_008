package com.ssts.ssts.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    //TODO 사용자한테 노출되는 예외랑 개발자한테만 보이는 예외를 나눠야할까?

    INPUT_NULL(RtnHttpStatus.Validation, 500, "입력값이 없어요."),
    IS_NULL(RtnHttpStatus.System, 501, "null값이예요."),
    INPUT_IS_NOT_ALLOWED(RtnHttpStatus.System, 502, "허용되지 않는 요청값이에요"),

    //사용자 관련 에러
    MEMBER_NOT_FOUND(RtnHttpStatus.System, 2000, "사용자를 찾을 수 없어요."),
    EMAIL_DUPLICATE(RtnHttpStatus.Validation, 2001, "이메일이 중복이예요."),
    PHONENUMBER_DUPLICATE(RtnHttpStatus.Validation,2002, "휴대폰 번호가 중복이예요."),
    NICKNAME_DUPLICATE(RtnHttpStatus.Validation,2008, "닉네임이 중복이예요."),

    //Auth 에러
    AUTH_NO_CREDENTIALS(RtnHttpStatus.Auth,2007,"해당 사용자의 정보가 없어요."),
    AUTH_NOT_SUPPORTED_SOCIAL_TYPE(RtnHttpStatus.Auth, 2008, "지원하는 소셜 로그인이 아니예요."),
    AUTH_TEST_LOGIN_NO_MEMBER(RtnHttpStatus.Auth,2009, "Test용 로그인 에러: 사용자 없음."),

    //jwt
    JWT_TOKEN_EXPIRED(RtnHttpStatus.Validation, 2010,"토큰이 만료됐어요."),
    JWT_NO_TOKEN(RtnHttpStatus.Validation, 2011, "토큰이 없어요."),
    JWT_NOT_VALID(RtnHttpStatus.Validation, 2012, "토큰이 유효하지 않아요."),

    //시리즈 에러
    SERIES_NOT_EXISTS(RtnHttpStatus.Validation, 2005, "시리즈를 찾을 수 없어요."),
    NOT_ALLOWED_PERMISSION(RtnHttpStatus.Validation,2010, "허용되지 않은 사용자예요."),

    //팔로우 에러
    FOLLOW_NOT_AVAILABLE(RtnHttpStatus.Validation,1050, "팔로우 할 수 없는 상대예요."),
    IS_ALREADY_FOLLOWING(RtnHttpStatus.Validation,1051,"이미 팔로우한 상대예요."),
    IS_ALREADY_UNFOLLOWING(RtnHttpStatus.Validation,1051,"팔로우한 상대가 아니예요."),

    //투표 에러
    MEMBER_ALREADY_VOTE(RtnHttpStatus.Validation,1001, "더 이상의 투표는 불가능해요."),
    VOTE_NOT_FOUND(RtnHttpStatus.Validation,1002, "존재하지 않는 투표예요."),
    CAN_NOT_MAKE_VOTE(RtnHttpStatus.Validation,1003, "더 이상 투표를 개설할 수 없어요."),
    DEADLINE_FALL_SHORT(RtnHttpStatus.Validation,1004, "이미 투표가 진행중이예요."),
    NOT_HAVE_VOTE_AUTHORITY(RtnHttpStatus.Validation,1005, "투표를 종료할 수 없는 사용자예요."),
    VOTE_RESULT_IS_NOT_EXSIST(RtnHttpStatus.Validation,1006, "투표 결과가 존재하지 않아요."),
    THIS_VOTE_RESULT_IS_TRUE(RtnHttpStatus.Validation,1007, "이 투표는 이미 졸업했어요!"),
    CAN_NOT_VOTE_VALUE(RtnHttpStatus.Validation,1008, "투표할 수 없는 값이예요."),
    NOT_SERISE_WRITER(RtnHttpStatus.Validation,1012, "시리즈를 작성한 본인이 아니라서 투표를 개설할 수 없어요."),
    VOTE_RESULT_NOT_UPDATE(RtnHttpStatus.Validation,1014,"최초투표의 결과가 조회되지 않았어요."),
    VOTE_ALREADY_FINISH(RtnHttpStatus.Validation,1015,"이미 투표가 종료되어 더 이상의 투표를 할 수 없어요."),
    VOTE_NOT_CREATE_DAYLOG(RtnHttpStatus.Validation,1016,"투표를 개설하기 전, 데이로그를 작성해 주세요."),
    VOTE_ALL_GRADUATE(RtnHttpStatus.Validation,1017,"이미 최대 투표를 진행했어요! 새로운 시리즈에 새 투표를 작성해 주세요."),
    VOTE_ALREADY_GRADUATE(RtnHttpStatus.Validation,1018,"축하해요! 최초 투표는 이미 졸업했어요! 투표를 종료시킬 수 없어요!"),


    //북마크 에러
    BOOKMARK_IS_DUPLICATION(RtnHttpStatus.Validation,1009, "이미 북마크한 시리즈예요."),
    BOOKMARKS_NOT_FOUND(RtnHttpStatus.Validation, 1013, "북마크한 시리즈가 없어요. 시리즈를 구경해 볼까요?"),

    //뱃지 에러
    BADGE_NOT_FOUND(RtnHttpStatus.Validation, 1010, "존재하지 않는 뱃지예요."),
    ALREADY_HAVE_BADGE(RtnHttpStatus.Validation, 1011, "이미 취득한 뱃지예요.");


    private RtnHttpStatus rtnHttpStatus;
    private int code;
    private String message;

}
