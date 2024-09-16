package com.example.demo.schedule.controller;

import com.example.demo.schedule.scheduledto.ScheduleInquiryResponseDto;
import com.example.demo.schedule.scheduledto.ScheduleRequestDto;
import com.example.demo.schedule.scheduledto.ScheduleResponseDto;
import com.example.demo.schedule.service.ScheduleService;
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

    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id ,@RequestBody ScheduleRequestDto scheduleRequestDto){

        return scheduleService.updateSchedule(id,scheduleRequestDto);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId){

        scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping("/full")
    public List<ScheduleResponseDto> fullInquirySchedule(){

        return scheduleService.fullInquirySchedule();
    }

}
