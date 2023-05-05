package com.ssts.ssts.domain.badges.controller;

import com.ssts.ssts.domain.badges.dto.BadgePostDto;
import com.ssts.ssts.domain.badges.entity.Badge;
import com.ssts.ssts.domain.badges.service.BadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("members/badge")
@Controller
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService){
        this.badgeService = badgeService;
    }

    //test API로 사용하지 않습니다
    @PostMapping
    public ResponseEntity postBadge(@RequestBody BadgePostDto postDto){
        Badge response = badgeService.saveBadge(postDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity getBadges(){
        List<Badge> response = badgeService.badgesList();

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/{badge_id}")
    public ResponseEntity getBadge(@PathVariable("badge_id") Long badgeId){

        Badge response = badgeService.findBadge(badgeId);

        return new ResponseEntity(response, HttpStatus.OK);
    }




}
