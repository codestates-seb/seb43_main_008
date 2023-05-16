package com.ssts.ssts.domain.bookmark.service;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import com.ssts.ssts.domain.bookmark.repository.BookmarkRepository;
import com.ssts.ssts.domain.bookmark.response.BookmarkResponse;
import com.ssts.ssts.domain.member.entity.Member;

import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<BookmarkResponse> getListBookmarks(){ //series.getId(), series.getTitle(), series.getDaylogCount(), series.getImage()
        Long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        List<Bookmark> bookmarks = bookmarkRepo.findAllByMemberId(memberId);


        List<BookmarkResponse> responses = bookmarks.stream()
                .map(bookmark ->
                        BookmarkResponse.of(
                                bookmark.getSeries().getId(),
                                bookmark.getSeries().getTitle(),
                                bookmark.getSeries().getDaylogCount(),
                                bookmark.getSeries().getImage()))
                .collect(Collectors.toList());
        return responses;
    }

    @Transactional
    public void deleteBookmark(Long seriseId){

        seriesRepo.findById(seriseId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        Long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        bookmarkRepo.deleteByMember_IdAndSeries_Id(memberId, seriseId);
    }
}
