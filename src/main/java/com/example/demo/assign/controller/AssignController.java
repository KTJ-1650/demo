package com.example.demo.assign.controller;

import com.example.demo.assign.dto.AssignRequestDto;
import com.example.demo.assign.dto.AssignResponseDto;
import com.example.demo.assign.service.AssignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assigns")
public class AssignController {

    private final AssignService assignService;

    @PostMapping("/{scheduleId}")
    public AssignResponseDto creatAssign(@PathVariable Long scheduleId, @RequestBody AssignRequestDto assignRequestDto){

        return assignService.creatAssign(scheduleId,assignRequestDto);
    }

    @GetMapping("/{assignId}")
    public AssignResponseDto inquiryAssign(@PathVariable Long assignId){

        return assignService.inquiryAssign(assignId);
    }

    @GetMapping
    public List<AssignResponseDto> fullInquiryAssign(){

        return assignService.fullInquiryAssign();
    }

    @DeleteMapping("/{assignId}")
    public void deleteAssign(@PathVariable Long assignId){

        assignService.deleteAssign(assignId);
    }
}
