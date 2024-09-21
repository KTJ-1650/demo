package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;
    private String email;
    private String token;
    private String message;

    public UserResponseDto(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public UserResponseDto(User user,String token){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.token = token;
    }

    public UserResponseDto(User user,String token, String message){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.token = token;
        this.message = message;
    }




}
