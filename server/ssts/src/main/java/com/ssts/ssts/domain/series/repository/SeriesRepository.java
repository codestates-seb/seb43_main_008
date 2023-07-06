package com.ssts.ssts.domain.series.repository;


import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.vote.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Series> findByMember_id(Long memberId, Pageable pageable);

    Page<Series> findByMember_idAndIsPublicTrue(Long memberId, Pageable pageable);

    @Query("SELECT s FROM Series s JOIN Vote v ON s = v.series WHERE s.isPublic = :isPublic AND v.createdAt IS NOT NULL")
    Page<Series> findAllByIsPublicAndVoteCreatedAtIsNotNull(Boolean isPublic, Pageable pageable);

    //TODO 조인 쿼리문 만들기.
    @Query("SELECT s FROM Series s JOIN Vote v ON s=v.series WHERE s.isPublic = :isPublic AND v.status = :status AND v.votePapers <> :limit")
    Page<Series> findAllByIsPublicAndStatusAndVotePapersNot(Boolean isPublic, Vote.VoteStatus status, int limit, Pageable pageable);

//
//    /* 멤버 서비스에서 멤버 아이디에 해당하는 시리즈 목록 출력 목적 */
//    List<Series> findAllByMemberId(long memberId);
    //TODO 아 장난하냐 아랫놈 때문에 서버 터져서 계속 난리칠 생각했네
    //Optional<Series> findByVote_Id(Long voteId);

    Optional<Series> findByVotes_Id(Long voteId);
}
