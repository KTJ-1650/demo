package com.example.demo.user.service;


import com.example.demo.config.JwtUtil;
import com.example.demo.config.PasswordEncoder;
import com.example.demo.config.UserRoleEnum;
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

        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자 입니다.");
        }

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }


        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        UserRoleEnum role = UserRoleEnum.USER;
        if(userRequestDto.getRole().equals("ADMIN")){
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(userRequestDto,encodedPassword,role);

       User savedUser  = userRepository.save(user);

       String token = jwtUtil.createToken(savedUser.getId(),savedUser.getUsername(),savedUser.getEmail(),savedUser.getRole());

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


    public UserResponseDto login(UserRequestDto userRequestDto) {

        User loginUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (loginUser == null) {
            throw new IllegalArgumentException("존재하지 않는 이메일 입니다.");
        }

        if (!passwordEncoder.matches(userRequestDto.getPassword(), loginUser.getPassword())) {
            throw new IllegalArgumentException("패스워드가 맞지 않습니다.");
        }


        String token = jwtUtil.createToken(loginUser.getId(),loginUser.getUsername(),loginUser.getEmail(),loginUser.getRole());

        return new UserResponseDto(loginUser,token,"로그인 했습니다.");
    }


}
