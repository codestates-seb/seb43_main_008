package com.ssts.ssts.domain.member.entity;

import com.ssts.ssts.domain.badges.entity.Badge;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "member_badge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberBadge extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="members_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="badges_id")
    private Badge badge;


    public static MemberBadge of(Member member, Badge badge){

        MemberBadge memberBadge = new MemberBadge();
        memberBadge.setMember(member);
        memberBadge.setBadge(badge);
        return memberBadge;
    }
}
