package com.ssts.ssts.domain.member.entity;

import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.vote.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "member_vote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="members_id")
    private Member member;

//    @Column
//    private Long memberId;

    @Column
    private int isAgree;

    @ManyToOne
    @JoinColumn(name="vote_id")
    private Vote vote;

//    @Column
//    private Long seriesId;


    //외래키 관계를 끊는 건 멘토에게 묻기로 함
    //oneToMany 만드는 순간 새로운 테이블을 만들게 됨
    //ㄴ> 엔티티를 만들거면 굳이 연관관계 해줄 필요가 없음

    //상대 Entity에 ManyToOne을 쓴다 >> 거기서 새 테이블을 생성한다, 따로 Entity를 만들 필요 없ㅇ므

    public static MemberVote of(Member member, Vote vote, int isAgree){
        MemberVote memberVote = new MemberVote();
        memberVote.setMember(member);
        memberVote.setVote(vote);
        memberVote.setIsAgree(isAgree);
        return memberVote;
    }



}
