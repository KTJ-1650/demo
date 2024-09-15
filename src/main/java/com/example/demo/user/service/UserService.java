package com.example.demo.user.service;

import com.example.demo.user.dto.UserRequestDto;
import com.example.demo.user.dto.UserResponseDto;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = new User(userRequestDto);

       User savedUser  = userRepository.save(user);

       return new UserResponseDto(savedUser);
    }

    public UserResponseDto inquiryUser(Long userId) {

      User foundUser =  userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("아이디가 존재하지 않습니다."));

      return new UserResponseDto(foundUser);
    }

    public List<UserResponseDto> fullInquiryUser() {

      List<User> foundUser  = userRepository.findAll();

      return foundUser.stream().map(UserResponseDto::new).toList();
    }

}
