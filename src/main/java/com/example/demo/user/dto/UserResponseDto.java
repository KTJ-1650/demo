package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;
    private String email;
    private String password;


    public UserResponseDto(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
