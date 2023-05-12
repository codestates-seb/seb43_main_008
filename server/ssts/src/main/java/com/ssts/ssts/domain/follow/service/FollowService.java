package com.ssts.ssts.domain.follow.service;

import com.ssts.ssts.domain.follow.dto.FollowingPostDto;
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

    public void following(FollowingPostDto followingPostDto) {
        Member following=memberService.findMemberByNickName(followingPostDto.getNickName());
        Member follower=memberService.findMemberByToken();

        Follow follow = Follow.of(follower.getId(), following.getId());
        followRepository.save(follow);
    }
}
