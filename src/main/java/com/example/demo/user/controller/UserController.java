package com.example.demo.user.controller;

import com.example.demo.user.dto.UserRequestDto;
import com.example.demo.user.dto.UserResponseDto;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){

        return userService.createUser(userRequestDto);
    }

    @GetMapping("/{userId}")
    public UserResponseDto inquiryUser(@PathVariable Long userId){

        return userService.inquiryUser(userId);
    }

    @GetMapping
    public List<UserResponseDto> fullInquiryUser(){

        return userService.fullInquiryUser();
    }
}
