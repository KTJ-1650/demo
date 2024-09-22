package com.example.demo.schedule.controller;

import com.example.demo.schedule.scheduledto.ScheduleInquiryResponseDto;
import com.example.demo.schedule.scheduledto.ScheduleRequestDto;
import com.example.demo.schedule.scheduledto.ScheduleResponseDto;
import com.example.demo.schedule.service.ScheduleService;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ScheduleResponseDto createSchedule( @RequestBody ScheduleRequestDto scheduleRequestDto){

        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @GetMapping("/{id}")
    public ScheduleInquiryResponseDto inquirySchedule(@PathVariable Long id){

        return scheduleService.inquirySchedule(id);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId , @RequestBody ScheduleRequestDto scheduleRequestDto){

        return scheduleService.updateSchedule(scheduleId,scheduleRequestDto);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId, User user){

        scheduleService.deleteSchedule(scheduleId,user);
    }

    @GetMapping("/full")
    public List<ScheduleResponseDto> fullInquirySchedule(){

        return scheduleService.fullInquirySchedule();
    }

}
