package com.example.demo.schedule.entity;

import com.example.demo.assign.entity.Assign;
import com.example.demo.comment.entity.Comment;
import com.example.demo.function.TimeStamp;
import com.example.demo.schedule.scheduledto.ScheduleRequestDto;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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


    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;


    @OneToMany(mappedBy = "schedule",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assign> assign;

    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> comment;



    public Schedule(ScheduleRequestDto scheduleRequestDto){

        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
    }


    public void include(List<Assign> assign){
        this.assign = assign;
    }



    public void update(ScheduleRequestDto scheduleRequestDto){

        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
    }



}
