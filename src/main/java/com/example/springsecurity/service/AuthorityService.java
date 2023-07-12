package com.example.springsecurity.service;

import com.example.springsecurity.model.Authority;
import com.example.springsecurity.repository.AuthorityRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRespository authorityRespository;

    public void deleteAll() {
        authorityRespository.deleteAll();
    }

    public void deleteAll(Iterable<Authority> entities) {
        authorityRespository.deleteAll(entities);
    }

    public List<Authority> getAll() {
        return authorityRespository.findAll();
    }

}
