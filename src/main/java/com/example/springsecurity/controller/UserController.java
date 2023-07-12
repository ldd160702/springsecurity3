package com.example.springsecurity.controller;

import com.example.springsecurity.model.Authority;
import com.example.springsecurity.model.UserEntity;
import com.example.springsecurity.service.AuthorityService;
import com.example.springsecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/user/profile")
    public ResponseEntity<UserEntity> getUserInformation(Authentication authentication, Model model) {
        String username = authentication.getName();

        UserEntity user =  userService.getUserByUsername(username);

        if (user == null) {
            model.addAttribute("message", "User does not exist");
            throw new RuntimeException("User does not exist");
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/admin/delete/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username) {
        UserEntity user = userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        userService.deleteUserByUsername(username);
    }


    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
}
