package com.example.demo.assign.service;

import com.example.demo.assign.dto.AssignRequestDto;
import com.example.demo.assign.dto.AssignResponseDto;
import com.example.demo.assign.entity.Assign;
import com.example.demo.assign.repository.AssignRepository;
import com.example.demo.schedule.entity.Schedule;
import com.example.demo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AssignService {

    private final AssignRepository assignRepository;
    private final ScheduleRepository scheduleRepository;

    public AssignResponseDto creatAssign(Long scheduleId,AssignRequestDto assignRequestDto) {

       Schedule foundSchedule = scheduleRepository.findById(scheduleId).
                orElseThrow(()->new NoSuchElementException("아이디가 존재하지 않습니다."));



        Assign assign = new Assign(foundSchedule,assignRequestDto);

       Assign savedAssign = assignRepository.save(assign);

        return new AssignResponseDto(savedAssign);
    }

    public AssignResponseDto inquiryAssign(Long assignId) {

        Assign assign = assignRepository.findById(assignId).
                orElseThrow(()->new NoSuchElementException("아이디가 존재하지 않습니다."));

        return new AssignResponseDto(assign);
    }

    public List<AssignResponseDto> fullInquiryAssign() {

       List<Assign> assign = assignRepository.findAll();

       return assign.stream().map(AssignResponseDto::new).toList();
    }

    public void deleteAssign(Long assignId) {

      Assign assign  = assignRepository.findById(assignId)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다"));

      assignRepository.delete(assign);
    }
}
