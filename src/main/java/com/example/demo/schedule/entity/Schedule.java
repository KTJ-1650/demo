package com.example.demo.schedule.entity;

import com.example.demo.comment.dto.CommentRequestDto;
import com.example.demo.comment.entity.Comment;
import com.example.demo.schedule.function.TimeStamp;
import com.example.demo.schedule.scheduledto.ScheduleRequestDto;
import com.example.demo.schedule.scheduledto.ScheduleResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedule")
@EntityListeners(AuditingEntityListener.class)
public class Schedule extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;


    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> comment;



    public Schedule(ScheduleRequestDto scheduleRequestDto){

        this.username = scheduleRequestDto.getUsername();
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
    }



    public void update(ScheduleRequestDto scheduleRequestDto){
        this.username = scheduleRequestDto.getUsername();
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
    }



}
