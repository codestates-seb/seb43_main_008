package com.ssts.ssts.domain.member.repository;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickName(String nickName);

    Optional<Member> findByPhone(String phone);

    Boolean existsById(long id);
}
