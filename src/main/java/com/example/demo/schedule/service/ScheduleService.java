package com.example.demo.schedule.service;

import com.example.demo.schedule.entity.Schedule;
import com.example.demo.schedule.repository.ScheduleRepository;
import com.example.demo.schedule.scheduledto.ScheduleRequestDto;
import com.example.demo.schedule.scheduledto.ScheduleResponseDto;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;

    public ScheduleResponseDto createSchedule(Long userId,ScheduleRequestDto scheduleRequestDto){

      User foundUser =  userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("유저 아이디가 존재하지 않습니다."));

        Schedule schedule = new Schedule(foundUser,scheduleRequestDto);

       Schedule savedSchedule  = scheduleRepository.save(schedule);

       return new ScheduleResponseDto(savedSchedule);
    }

    public ScheduleResponseDto inquirySchedule(Long id) {

       Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

       return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {

      Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

      schedule.update(scheduleRequestDto);

      return new ScheduleResponseDto(schedule);
    }


    public void deleteSchedule(Long scheduleId) {

      Schedule foundSchedule  =  scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

      scheduleRepository.delete(foundSchedule);
    }
}
