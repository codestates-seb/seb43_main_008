package com.ssts.ssts.domain.member.repository;

import com.ssts.ssts.domain.member.constant.MemberStatus;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.global.auth.utils.SocialType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);

    Optional<Member> findByIdAndMemberStatus(long id, MemberStatus memberStatus);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickName(String nickName);

    Optional<Member> findByPhone(String phone);

}
