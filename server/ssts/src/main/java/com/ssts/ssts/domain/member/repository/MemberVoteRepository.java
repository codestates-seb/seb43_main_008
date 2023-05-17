package com.ssts.ssts.domain.member.repository;

import com.ssts.ssts.domain.member.entity.MemberVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberVoteRepository extends JpaRepository<MemberVote, Long> {
    Optional<Long> findByMember_id(Long memberId);

    //    //seriesId를 받으면 voteMember에 저장된 memberid를 전부 지우기
    //1. series Id를 통해 memberVote의 id 찾기
    List<MemberVote> findBySeries_Id(Long seriesId);

    void deleteAllBySeries_Id(Long seriesId);

    Boolean existsByMember_IdAndSeries_Id(Long memberId, Long seriesId);

    //VoteEndTime이 현재 시간 이전이면 true, 그렇지 않으면 fasle
    //Boolean existsByVoteEndAtBefore(Date date);
    //ㄴ> 시리즈 레포로 가야함
}
