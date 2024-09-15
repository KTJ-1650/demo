package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime  modifiedAt;

    public UserResponseDto(User user){

        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
