package com.ssts.ssts.domain.follow.repository;

import com.ssts.ssts.domain.follow.entity.Follow;
import com.ssts.ssts.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    void deleteByFollowerIdAndFollowingId(long followerId, long followingId);
//    Page<Follow> findAllByFollower(long follower, Pageable pageable);
//    Page<Follow> findAllByFollowing(long following, Pageable pageable);
    Boolean existsByFollowerIdAndFollowingId(long followerId, long followingId);

    Page<Follow> findAllByFollower(Member follower, Pageable pageable);
    Page<Follow> findAllByFollowing(Member following, Pageable pageable);

}
