package com.ssts.ssts.domain.bookmark.controller;

import com.ssts.ssts.domain.bookmark.entity.Bookmark;
import com.ssts.ssts.domain.bookmark.response.BookmarkResponse;
import com.ssts.ssts.domain.bookmark.service.BookmarkService;
import com.ssts.ssts.domain.series.entity.Series;
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

    @PostMapping("/{serise_id}")
    public ResponseEntity postBookmark(@PathVariable("serise_id") Long seriesId){

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
