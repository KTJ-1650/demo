package com.example.demo.assign.repository;

import com.example.demo.assign.entity.Assign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignRepository extends JpaRepository<Assign,Long> {

}
