package com.ssts.ssts.domain.follow.service;

import com.ssts.ssts.domain.follow.dto.FollowListResponse;
import com.ssts.ssts.domain.follow.dto.OpponentNickNameDto;
import com.ssts.ssts.domain.follow.entity.Follow;
import com.ssts.ssts.domain.follow.repository.FollowRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberService memberService;

    public Boolean isMemberFollowing(long followerId, long followingId){

        boolean isFollowing=followRepository.existsByFollowerIdAndFollowingId(followerId,followingId);

        return isFollowing;
    }

    public void following(String nickName) {

        memberService.findMemberByNickName(nickName);

        Member following=memberService.findMemberByNickName(nickName); //상대방
        //이 과정에서 유효한 멤버 닉네임인지 확인하게 된다.
        Member follower=memberService.findMemberByToken(); //본인

        log.info("하늘/follow service: following"+
                "\nfollower="+follower.getId()+
                "\nfollowing="+following.getId());

        //boolean isFollowing=followRepository.existsByFollowerIdAndFollowingId(follower.getId(),following.getId());
        boolean isFollowing=isMemberFollowing(follower.getId(), following.getId());

        //팔로잉이 본인이거나, 탈퇴한 사용자일 경우(탈퇴한 사용자가 화면에 뜰일은 없을 거 같긴한데..혹시 모르니까)
        if(following.getId()==follower.getId() || following.getStatus()== Member.Status.WITHDRAW){
            throw new BusinessLogicException(ExceptionCode.FOLLOW_NOT_AVAILABLE);
        }else if(isFollowing){ //이미 팔로잉한 사용자일 경우(혹시 모르니까2)
            throw new BusinessLogicException(ExceptionCode.IS_ALREADY_FOLLOWING);
        }

        Follow follow = Follow.of(follower, following);
        //Follow follow = Follow.of(follower.getId(), following.getId());
        followRepository.save(follow);
    }

    // TODO @Transcational이 필요한 이유? EntityManager란??
    // javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call
    @Transactional
    public void unfollowing(String nickName){

        memberService.findMemberByNickName(nickName);

        Member unfollowing = memberService.findMemberByNickName(nickName); //상대방
        Member follower = memberService.findMemberByToken(); //본인

        log.info("하늘/follow service: unfollowing"+
                "\nfollower="+follower.getId()+
                "\nunfollowing="+unfollowing.getId());

        //boolean isFollowing=followRepository.existsByFollowerIdAndFollowingId(follower.getId(), unfollowing.getId());
        boolean isFollowing=isMemberFollowing(follower.getId(), unfollowing.getId());
        //존재하면, 언팔로우가 가능하다.
        //존재하지 않는다면, 언팔로우가 불가능하다.

        //팔로잉이 본인이거나, 탈퇴한 사용자일 경우(탈퇴한 사용자가 화면에 뜰일은 없을 거 같긴한데..혹시모르니까)
        if(unfollowing.getId()==follower.getId() || unfollowing.getStatus()== Member.Status.WITHDRAW){
            throw new BusinessLogicException(ExceptionCode.FOLLOW_NOT_AVAILABLE);
        } else if (!isFollowing) { //팔로잉한 사용자가 아닐 경우(혹시 모르니까2)
            throw new BusinessLogicException(ExceptionCode.IS_ALREADY_UNFOLLOWING);
        }

        followRepository.deleteByFollowerIdAndFollowingId(follower.getId(), unfollowing.getId());

    }

    public PageResponseDto followingList(int page, int size){

        Member follower=memberService.findMemberByToken(); //본인

        log.info("하늘/follow service: followingList"+
                "\nfollower="+follower.getId());

        Page<Follow> pageinfo=followRepository.findAllByFollower(
                follower,
                PageRequest.of(page, size));

        List<FollowListResponse> responseList = pageinfo.getContent().stream()
                .map(follow -> FollowListResponse.of(
                        follow.getFollowing().getNickName(),
                        follow.getFollowing().getIntroduce(),
                        follow.getFollowing().getImage()))
                .collect(Collectors.toList());

        return new PageResponseDto(responseList, pageinfo);

    }

    public PageResponseDto followerList(int page, int size){

        Member follower=memberService.findMemberByToken(); //본인

        log.info("하늘/follow service: followerList"+
                "\nfollower="+follower.getId());

        Page<Follow> pageinfo=followRepository.findAllByFollowing(
                follower,
                PageRequest.of(page, size));

        List<FollowListResponse> responseList=pageinfo.getContent().stream()
                .map(follow -> FollowListResponse.of(
                        follow.getFollower().getNickName(),
                        follow.getFollower().getIntroduce(),
                        follow.getFollower().getImage()))
                .collect(Collectors.toList());

        return new PageResponseDto(responseList, pageinfo);

    }
}
