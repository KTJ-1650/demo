package com.example.demo.user.service;


import com.example.demo.config.JwtUtil;
import com.example.demo.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        User user = new User(userRequestDto,encodedPassword);

       User savedUser  = userRepository.save(user);

       String token = jwtUtil.createToken(savedUser.getId(),savedUser.getUsername(),savedUser.getEmail());

       return new UserResponseDto(savedUser,token);
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
