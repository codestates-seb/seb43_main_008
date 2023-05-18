package com.ssts.ssts.domain.badges.service;

import com.ssts.ssts.domain.badges.BadgeResponse;
import com.ssts.ssts.domain.badges.dto.BadgePostDto;
import com.ssts.ssts.domain.badges.entity.Badge;
import com.ssts.ssts.domain.badges.repository.BadgeRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.entity.MemberBadge;
import com.ssts.ssts.domain.member.repository.MemberBadgeRepository;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepo;
    private final MemberRepository memberRepo;
    private final MemberBadgeRepository memberBadgeRepo;

   @Transactional //testAPI: 실제로 사용하지 않습니다
    public Badge saveBadge(BadgePostDto postDto){
        Badge badge = Badge.of(
                postDto.getName(),
                postDto.getEarnMainText(),
                postDto.getEarnSubText(),
                postDto.getUnEarnMainText(),
                postDto.getUnEarnSubText(),
                postDto.getIsAcquired(),
                postDto.getImg()
        );
        Badge saveBadge = badgeRepo.save(badge);
    return saveBadge;
    }

    @Transactional
    public MemberBadge saveBadgeMember(Long badgeId){

       //사용자 검증
        Long memberId = SecurityUtil.getMemberId();
        Member member = memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Badge badge = badgeRepo.findById(badgeId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.BADGE_NOT_FOUND));

        Boolean isBadge = memberBadgeRepo.existsByMember_IdAndBadgeId(memberId, badgeId);
        if(isBadge){throw new BusinessLogicException(ExceptionCode.ALREADY_HAVE_BADGE);}

        //해당 뱃지를 해당 유저가 보유하고 있다고 뱃지의 값을 바꿔줌
        badge.setIsAcquired(true);

        //멤버와 뱃지를 맵핑 테이브에 등록
        MemberBadge response = memberBadgeRepo.save(MemberBadge.of(member, badge));
        return response;
    }


    //뱃지 상세조회
    public BadgeResponse findBadge(Long badgeId){

        Long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Badge badge = badgeRepo.findById(badgeId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.BADGE_NOT_FOUND));

        //Boolean isBadged  = memberBadgeRepo.existsByMember_IdAndBadgeId(memberId, badgeId);

        return isAcquiredResponse(badge);
    }

    public List<Badge> findAllBadge(){
        Long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        List<MemberBadge> badgeInfos = memberBadgeRepo.findAllByMember_Id(memberId);

        List<Badge> badges = badgeInfos.stream()
                .map(badgeInfo ->
                        badgeInfo.getBadge())
                .collect(Collectors.toList());

        return badges;
    }


    public BadgeResponse isAcquiredResponse(Badge badge){
       if(badge.getIsAcquired()){
           return BadgeResponse.isAcquiredResponse.of(
                   badge.getId(),
                   badge.getName(),
                   badge.getEarnMainText(),
                   badge.getEarnSubText(),
                   badge.getImg(),
                   badge.getIsAcquired()
           );
       } else if (!badge.getIsAcquired()) {
           return BadgeResponse.unAcquiredResponse.of(
                   badge.getId(),
                   badge.getName(),
                   badge.getUnEarnMainText(),
                   badge.getUnEarnSubText(),
                   badge.getImg(),
                   badge.getIsAcquired()
           );
       }else {throw new BusinessLogicException(ExceptionCode.BADGE_NOT_FOUND); }
    }

}
