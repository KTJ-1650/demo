package com.example.demo.comment.controller;

import com.example.demo.comment.dto.CommentRequestDto;
import com.example.demo.comment.dto.CommentResponseDto;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{scheduleId}")
    public CommentResponseDto createComment(@PathVariable Long scheduleId,@RequestBody CommentRequestDto commentRequestDto){

        return commentService.createComment(scheduleId,commentRequestDto);
    }

    @GetMapping("/{id}")
    public CommentResponseDto inquiryComment(@PathVariable Long id){

        return commentService.inquiryComment(id);
    }

    @GetMapping("/full/{scheduleId}")
    public List<CommentResponseDto> fullInquiryComment(@PathVariable Long scheduleId){

        return commentService.fullInquiryComment(scheduleId);
    }

    @DeleteMapping("{commentId}")
    public void deleteComment(@PathVariable Long commentId){

        commentService.deleteComment(commentId);
    }
}
