package com.example.demo.user.entity;

import com.example.demo.function.TimeStamp;
import com.example.demo.schedule.entity.Schedule;
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


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;

    public User(UserRequestDto userRequestDto){
        this.username = userRequestDto.getUsername();
        this.email = userRequestDto.getEmail();

    }
}
