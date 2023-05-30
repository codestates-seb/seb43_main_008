package com.ssts.ssts.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.comment.Entity.Comment;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.member.constant.MemberStatus;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.global.auth.utils.SocialType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name="members")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private SocialType socialType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus=MemberStatus.ACTIVE;

    @Column
    private String introduce="내 소개글을 작성해봐요 !";

    @Column
    String image;

    @JsonIgnore
    @OneToMany(mappedBy ="member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Series> series;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Daylog> daylogs;

    public static Member of(String email,String nickName, String phone) {
        Member member=new Member();

        member.setEmail(email);
        member.setNickName(nickName);
        member.setPhone(phone);

        return member;
    }


}
