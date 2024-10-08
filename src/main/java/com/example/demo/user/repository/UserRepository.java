package com.example.demo.user.repository;

import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByEmail(String email);


    Optional<User> findByUsername(String subject);
}
