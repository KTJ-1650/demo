package com.example.demo.weather.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;

    public String getTodayWeather() {

        URI uri = UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json")
                .encode()
                .build()
                .toUri();

        ResponseEntity<List<Map<String, String>>> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, String>>>() {}
        );


        List<Map<String,String>> weatherData = responseEntity.getBody();

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd"));

        return weatherData.stream()
                .filter(data -> today.equals(data.get("date")))
                .map(data ->data.get("weather"))
                .findFirst()
                .orElse("Unknown");
    }
}