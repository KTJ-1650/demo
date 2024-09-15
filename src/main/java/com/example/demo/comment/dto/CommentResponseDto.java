package com.example.demo.comment.dto;

import com.example.demo.comment.entity.Comment;
import com.example.demo.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommentResponseDto(Comment comment){
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }


}
