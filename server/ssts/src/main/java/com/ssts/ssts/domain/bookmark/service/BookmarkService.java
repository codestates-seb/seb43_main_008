package com.ssts.ssts.domain.bookmark.service;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import com.ssts.ssts.domain.bookmark.repository.BookmarkRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.entity.MemberVote;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepo;
    private final MemberRepository memberRepo;

    private final SeriesRepository seriesRepo;



    @Transactional
    public Bookmark createBookmark(Long seriseId){

        Series series = seriesRepo.findById(seriseId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        Long memberId = SecurityUtil.getMemberId();
        Member member = memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Bookmark bookmark = Bookmark.of(member, series);
         bookmarkRepo.save(bookmark);
         return bookmark;
    }
}
