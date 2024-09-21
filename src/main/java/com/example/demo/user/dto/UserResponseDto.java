package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;
    private String email;

    private String token;


    public UserResponseDto(User user,String token){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.token = token;
    }

    public UserResponseDto(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }


}
