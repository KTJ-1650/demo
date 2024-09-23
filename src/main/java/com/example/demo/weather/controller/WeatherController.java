package com.example.demo.weather.controller;


import com.example.demo.weather.dto.ItemDto;
import com.example.demo.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping("/today")
    public ResponseEntity<String> getTodayWeather() {
        String weather = weatherService.getTodayWeather();

        return ResponseEntity.ok(weather);

    }
}