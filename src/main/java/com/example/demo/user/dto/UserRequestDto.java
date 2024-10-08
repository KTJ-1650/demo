package com.example.demo.user.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private String username;
    private String email;
    private String password;
    private String role;
}
