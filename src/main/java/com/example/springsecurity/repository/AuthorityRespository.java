package com.example.springsecurity.repository;

import com.example.springsecurity.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRespository extends JpaRepository<Authority, Long> {
}
