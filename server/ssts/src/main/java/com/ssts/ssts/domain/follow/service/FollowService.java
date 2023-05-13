package com.ssts.ssts.domain.follow.service;

import com.ssts.ssts.domain.follow.dto.OpponentNickNamePostDto;
import com.ssts.ssts.domain.follow.entity.Follow;
import com.ssts.ssts.domain.follow.repository.FollowRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberService memberService;

    public void following(OpponentNickNamePostDto OpponentNickNamePostDto) {
        Member following=memberService.findMemberByNickName(OpponentNickNamePostDto.getNickName());
        Member follower=memberService.findMemberByToken();

        Follow follow = Follow.of(follower.getId(), following.getId());
        followRepository.save(follow);
    }

    @Transactional
    public void unfollowing(OpponentNickNamePostDto opponentNickNamePostDto){
        Member unfollowing = memberService.findMemberByNickName(opponentNickNamePostDto.getNickName());
        Member follower = memberService.findMemberByToken();

        followRepository.deleteByFollowerAndFollowing(follower.getId(), unfollowing.getId());

    }
}
