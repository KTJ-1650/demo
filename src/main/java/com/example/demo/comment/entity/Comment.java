package com.example.demo.comment.entity;

import com.example.demo.comment.dto.CommentRequestDto;
import com.example.demo.schedule.entity.Schedule;
import com.example.demo.function.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Comment extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;


    @Column(name = "username")
    private String username;


    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    public void scheduleComment(Schedule schedule){

        this.schedule = schedule;
    }


    public Comment(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getContent();
        this.username = commentRequestDto.getUsername();
    }


}
