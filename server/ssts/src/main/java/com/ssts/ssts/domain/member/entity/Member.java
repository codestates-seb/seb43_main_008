package com.ssts.ssts.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name="members")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //post 입력받음---------------------------
    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; //encoder 작업
    //---------------------------------------

    @ElementCollection(fetch = FetchType.EAGER)
    // 값 타입 컬렉션(값 타입을 컬렉션에 담아서 사용할때) -> 1:N 관계 (별도의 테이블 생성해서 컬렉션 관리)
    private List<String> roles = new ArrayList<>();

    // 자동 입력-------------------------------
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Status status=Status.ACTIVE;

//
//    @Column(nullable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @Column(nullable = false)
//    private LocalDateTime modifiedAt = LocalDateTime.now();
    //---------------------------------------

    // null 가능------------------------------
    @Column
    private String introduce="내 소개글을 작성해봐요 !";

    @Column
    String image;
    //---------------------------------------


    //FetchType.EAGER 전략 : 항상 목록 가져오기 (불필요한 조회를 막으려면 FetchType.LAZY)
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_id")
    private List<Series> series;

    // of 정적 팩토리 메서드 - 임시 회원가입
    public static Member of(String nickName, String email, String password) {
        Member member=new Member();

        member.setNickName(nickName);
        member.setEmail(email);
        member.setPassword(password);

        return member;
    }



    // enum
    public enum Status{
        ACTIVE("활동",0),
        DORMANCY("휴면",1),
        WITHDRAW("탈퇴",2);


        @Getter
        private String strStatus;
        @Getter
        private int numStatus;

        Status(String strStatus, int numStatus){
            this.strStatus = strStatus;
            this.numStatus = numStatus;
        }

        // 상수로만 값을 넣기 위한 메서드
        public static Status fromInt(int numStatus) {
            for (Status value : values()) {
                if (value.numStatus == numStatus) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid num: " + numStatus);
        }

        // 문자열로 값을 넣기 위한 메서드
        public static Status fromString(String strStatus) {
            for (Status value : values()) {
                if (value.strStatus.equals(strStatus)) {

                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid str: " + strStatus);
        }



    }

}
