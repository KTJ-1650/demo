package com.example.demo.schedule.service;

import com.example.demo.assign.entity.Assign;
import com.example.demo.assign.repository.AssignRepository;
import com.example.demo.config.UserRoleEnum;
import com.example.demo.schedule.entity.Schedule;
import com.example.demo.schedule.repository.ScheduleRepository;
import com.example.demo.schedule.scheduledto.ScheduleInquiryResponseDto;
import com.example.demo.schedule.scheduledto.ScheduleRequestDto;
import com.example.demo.schedule.scheduledto.ScheduleResponseDto;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.weather.entity.Weather;
import com.example.demo.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AssignRepository assignRepository;
    private final WeatherService weatherService;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto){

        String weather = weatherService.getTodayWeather();

        Schedule schedule = new Schedule(scheduleRequestDto,weather);

       Schedule savedSchedule  = scheduleRepository.save(schedule);

       return new ScheduleResponseDto(savedSchedule);
    }

    public ScheduleInquiryResponseDto inquirySchedule(Long id) {

       Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

       List<Assign> listAssign = assignRepository.findAllByScheduleId(id);

       return new ScheduleInquiryResponseDto(schedule,listAssign);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto) {

      Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));



      schedule.update(scheduleRequestDto);

      return new ScheduleResponseDto(schedule);
    }


    public void deleteSchedule(Long scheduleId, User user) {

      Schedule foundSchedule  =  scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

      if(!user.getRole().equals(UserRoleEnum.ADMIN)) {
          throw new IllegalArgumentException("삭제할 권한이 없습니다.");
      }

      scheduleRepository.delete(foundSchedule);
    }

    public List<ScheduleResponseDto> fullInquirySchedule() {

        List<Schedule> foundSchedule = scheduleRepository.findAll();

        return foundSchedule.stream().map(ScheduleResponseDto::new).toList();
    }
}
