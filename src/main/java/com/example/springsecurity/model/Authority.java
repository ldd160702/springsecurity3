package com.example.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority {


//    @ManyToOne
//    @JoinColumn(name = "username")
//    private UserEntity user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority")
    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    @JsonIgnore
    private UserEntity user;

    public Authority() {
    }


    public Authority(String role, UserEntity user) {
        this.role = role;
        this.user = user;
    }

    public Authority(String role) {
        this.role = role;
    }

    public Authority(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Authority(Long id, String role, UserEntity user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
