package com.ssts.ssts.domain.comment.service;


import com.ssts.ssts.domain.comment.Entity.Comment;
import com.ssts.ssts.domain.comment.dto.CommentPostDto;
import com.ssts.ssts.domain.comment.dto.CommentResponseDto;
import com.ssts.ssts.domain.comment.dto.CommentUpdateDto;
import com.ssts.ssts.domain.comment.repository.CommentRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.service.SeriesService;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import com.ssts.ssts.global.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final SeriesService seriesService;
    private final MemberService memberService;
    private final UpdateUtils<Comment> updateUtils;

    public PageResponseDto getCommentList(Long seriesId, int page, int size){

        Member findMember = memberService.findMemberByToken();

        Page<Comment> commentInfo = commentRepository.findBySeries_id(seriesId, PageRequest.of(page, size,
                Sort.by("id").descending()));

        List<Comment> comments = commentInfo.getContent();
        List<CommentResponseDto> list = this.commentListToResponseDtoList(comments);

        return new PageResponseDto<>(list,commentInfo);
    }

    @Transactional
    public CommentResponseDto saveComment(Long seriesId, CommentPostDto commentPostDto){
        Comment comment = Comment.of(commentPostDto.getComment());
        Series findSeries = seriesService.findVerifiedSeries(seriesId);
        Member findMember = memberService.findMemberByToken();

        comment.addSeries(findSeries);
        comment.addMember(findMember);


        commentRepository.save(comment);

        return this.commentToResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long seriesId, Long commentId, CommentUpdateDto commentUpdateDto){
        Comment descComment = this.findVerifiedComment(commentId);
        Comment comment = Comment.of(commentUpdateDto.getComment());

        if(memberService.findMemberByToken().getId() !=  descComment.getMember().getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }
        log.info("실행됌");
        Comment updateComment = updateUtils.copyNonNullProperties(comment, descComment);
        commentRepository.save(updateComment);

        return this.commentToResponseDto(updateComment);
    }

    @Transactional
    public void deleteComment(Long commentId){
        Comment comment = this.findVerifiedComment(commentId);
        if(memberService.findMemberByToken().getId() !=  comment.getMember().getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        commentRepository.delete(comment);

    }



    private CommentResponseDto commentToResponseDto(Comment comment){

        return CommentResponseDto.of(comment);
    }

    private List<CommentResponseDto> commentListToResponseDtoList(List<Comment> commentList){
        if(commentList == null){
            return null;
        }
        List<CommentResponseDto> list = new ArrayList<>(commentList.size());
        Iterator iterator = commentList.iterator();

        while (iterator.hasNext()){
            Comment comment = (Comment) iterator.next();
            list.add(this.commentToResponseDto(comment));
        }

        return list;
    }

    public Comment findVerifiedComment(Long commentId){
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        Comment findComment =
                optionalComment.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        return findComment;

    }

}
