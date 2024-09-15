package com.example.demo.assign.dto;

import com.example.demo.assign.entity.Assign;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class AssignResponseDto {
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime  modifiedAt;

    public AssignResponseDto(Assign assign){
        this.username = assign.getUsername();
        this.email = assign.getEmail();
        this.createdAt = assign.getCreatedAt();
        this.modifiedAt = assign.getModifiedAt();
    }
}
