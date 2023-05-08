package com.ssts.ssts.domain.badges.service;

import com.ssts.ssts.domain.badges.dto.BadgePostDto;
import com.ssts.ssts.domain.badges.entity.Badge;
import com.ssts.ssts.domain.badges.repository.BadgeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {

    private BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository){
        this.badgeRepository =  badgeRepository;
    }

    //testAPI: 실제로 사용하지 않습니다
    public Badge saveBadge(BadgePostDto postDto){
        Badge badge = Badge.of(
                postDto.getName(),
                postDto.getDescription()
        );
        Badge saveBadge = badgeRepository.save(badge);
    return saveBadge;
    }

    public List<Badge> badgesList(){
        List<Badge> badgeList = badgeRepository.findAll();
        return badgeList;
    }


    public Badge findBadge(Long badgeId){

        Optional<Badge> byId = badgeRepository.findById(badgeId);
        Badge badge = byId.get();

        return badge;
    }
}
