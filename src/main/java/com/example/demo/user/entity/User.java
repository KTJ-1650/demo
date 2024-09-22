package com.example.demo.user.entity;

import com.example.demo.assign.entity.Assign;
import com.example.demo.config.UserRoleEnum;
import com.example.demo.function.TimeStamp;
import com.example.demo.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class User extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String username;
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

   /* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<Assign> assigns;

    public User(UserRequestDto userRequestDto , String encodedPassword){
        this.username = userRequestDto.getUsername();
        this.email = userRequestDto.getEmail();
        this.password = encodedPassword;

    }

    public User(UserRequestDto userRequestDto, String encodedPassword, UserRoleEnum role) {
        this.username = userRequestDto.getUsername();
        this.email = userRequestDto.getEmail();
        this.password = encodedPassword;
        this.role = role;
    }






}
