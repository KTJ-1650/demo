package com.example.demo.schedule.scheduledto;

import com.example.demo.assign.entity.Assign;
import com.example.demo.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleResponseDto {


    private String title;
    private String content;
    private String weather;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ScheduleResponseDto(Schedule schedule){

        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.weather = schedule.getWeather();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
