package com.ssts.ssts.domain.bookmark.service;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import com.ssts.ssts.domain.bookmark.repository.BookmarkRepository;
import com.ssts.ssts.domain.bookmark.response.BookmarkResponse;
import com.ssts.ssts.domain.member.entity.Member;

import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepo;
    private final MemberRepository memberRepo;
    private final SeriesRepository seriesRepo;
    private final MemberService memberService;



    @Transactional
    public void createBookmark(Long seriseId){

        Series series = seriesRepo.findById(seriseId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        Member member = memberService.findMemberByToken();
        long memberId = member.getId();

        seriesRepo.findById(seriseId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        //북마크 중복 체크
        Boolean duplicationCheck = bookmarkRepo.existsByMember_IdAndSeries_Id(memberId, seriseId);
        if (duplicationCheck){ throw new BusinessLogicException(ExceptionCode.BOOKMARK_IS_DUPLICATION); }

        Bookmark bookmark = Bookmark.of(member, series);
        bookmarkRepo.save(bookmark);
    }

    @Transactional
    public List<BookmarkResponse> getListBookmarks(int page, int size){ //series.getId(), series.getTitle(), series.getDaylogCount(), series.getImage()
        Member member = memberService.findMemberByToken();
        long memberId = member.getId();

        PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Bookmark> bookmarksInfo = bookmarkRepo.findAllByMemberId(memberId, pageable);

        List<Bookmark> bookmarks = bookmarksInfo.getContent();

        if(bookmarks.isEmpty()){throw new BusinessLogicException(ExceptionCode.BOOKMARKS_NOT_FOUND);}

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

        Member member = memberService.findMemberByToken();
        long memberId = member.getId();

        bookmarkRepo.deleteByMember_IdAndSeries_Id(memberId, seriseId);
    }
}
