package com.ssts.ssts.domain.bookmark.controller;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import com.ssts.ssts.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{serise_id}")
    public ResponseEntity postBookmark(@PathVariable("serise_id") Long seriesId){

        Bookmark response = bookmarkService.createBookmark(seriesId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
