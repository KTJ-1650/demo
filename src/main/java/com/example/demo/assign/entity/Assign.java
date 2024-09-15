package com.example.demo.assign.entity;

import com.example.demo.assign.dto.AssignRequestDto;
import com.example.demo.function.TimeStamp;
import com.example.demo.schedule.entity.Schedule;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Assign extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Assign(Schedule schedule,AssignRequestDto assignRequestDto){
        this.schedule = schedule;
        this.username = assignRequestDto.getUsername();
        this.email = assignRequestDto.getEmail();
    }

}
