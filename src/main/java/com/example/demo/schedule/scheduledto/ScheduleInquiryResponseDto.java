package com.example.demo.schedule.scheduledto;

import com.example.demo.assign.entity.Assign;
import com.example.demo.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class ScheduleInquiryResponseDto {

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<Assign> assignList;

    public ScheduleInquiryResponseDto(Schedule schedule, List<Assign> assignList){

        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();

        this.assignList = assignList.stream().map(assign -> new Assign(assign.getId(), assign.getUsername(), assign.getEmail()))
                .toList();


    }
}
