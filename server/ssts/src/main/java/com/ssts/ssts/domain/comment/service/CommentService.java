package com.ssts.ssts.domain.comment.service;


import com.ssts.ssts.domain.comment.repository.CommentRepository;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    public PageResponseDto getCommentList(int page, int sizt){

        return null;
    }

}
