package com.example.demo.schedule.scheduledto;

import com.example.demo.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private String username;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ScheduleResponseDto(Schedule schedule){
        this.username = schedule.getUsername();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
