package com.example.demo.weather.entity;


import lombok.Getter;

@Getter
public class Weather {
    private String username;
    private String password;

    public Weather(String username, String password) {
        this.username = username;
        this.password = password;
    }
}