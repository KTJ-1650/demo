package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPasswordResponseDto {

    private final String bearerToken;


    public UserPasswordResponseDto(String bearerToken) {
        this.bearerToken = bearerToken;
    }


}
