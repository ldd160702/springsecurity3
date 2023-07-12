package com.example.springsecurity.repository;

import com.example.springsecurity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    void deleteByUsername(String username);
}
