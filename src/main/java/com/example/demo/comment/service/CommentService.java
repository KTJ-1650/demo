package com.example.demo.comment.service;

import com.example.demo.comment.dto.CommentRequestDto;
import com.example.demo.comment.dto.CommentResponseDto;
import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.schedule.entity.Schedule;
import com.example.demo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;



    public CommentResponseDto createComment(Long scheduleId,CommentRequestDto commentRequestDto) {

       Schedule schedule = scheduleRepository.findById(scheduleId).
               orElseThrow(()->new NoSuchElementException("아이디를 찾을 수 없습니다."));

        Comment comment = new Comment(commentRequestDto);

        comment.scheduleComment(schedule);  //1번 스케쥴에   댓글을 연결한거

        Comment savedComment = commentRepository.save(comment);
//
       return new CommentResponseDto(savedComment);
    }

    public CommentResponseDto inquiryComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

        return new CommentResponseDto(comment);
    }


    public List<CommentResponseDto> fullInquiryComment(Long scheduleId) {

       List<Comment> comment  = commentRepository.findAllByScheduleId(scheduleId);


       return comment.stream().map(CommentResponseDto::new).toList();

    }

    public void deleteComment(Long commentId) {

        if(!commentRepository.existsById(commentId)){
            throw new IllegalArgumentException("삭제할 데이터가 없습니다.");
        }

        commentRepository.deleteById(commentId);
    }

    public Page<CommentResponseDto> pageComment(int page, int size) {

        Pageable pageable = PageRequest.of(page -1,size);

        Page<Comment> commentPage = commentRepository.findAllByOrderByModifiedAtDesc(pageable);

        Page<CommentResponseDto> commentResponseDtos =commentPage.map(comment -> new CommentResponseDto(comment));

        return commentResponseDtos;
    }
}
