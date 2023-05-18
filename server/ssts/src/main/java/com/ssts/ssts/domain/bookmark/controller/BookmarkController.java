package com.ssts.ssts.domain.bookmark.controller;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import com.ssts.ssts.domain.bookmark.response.BookmarkResponse;
import com.ssts.ssts.domain.bookmark.service.BookmarkService;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final MemberRepository memberRepo;
    private final SeriesRepository seriesRepo;

    @PostMapping("/{serise_id}")
    public ResponseEntity postBookmark(@PathVariable("serise_id") Long seriesId){

        Long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Bookmark response = bookmarkService.createBookmark(seriesId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getBookmarks(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "12") int size){
        List<BookmarkResponse> response = bookmarkService.getListBookmarks(page-1, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{serise_id}")
    public ResponseEntity deleteBookmark(@PathVariable("serise_id") Long seriseId){

        bookmarkService.deleteBookmark(seriseId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
