package com.example.springsecurity.service;

import com.example.springsecurity.model.UserEntity;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is disabled");
        }

//        if (user.getUsername().equals("root")) {
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.isEnabled(),
//                    true,
//                    true,
//                    true,
//                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
//        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(user.getRoles().toArray(new String[0])));
    }

    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        Iterable<UserEntity> iterable = userRepository.findAll();
        iterable.forEach(users::add);
        return users;
    }

    public UserEntity getUserById(String username) {
        UserEntity user = userRepository.findById(username).get();
        return user;
    }

    public void deleteUserById(String username) {
        userRepository.deleteById(username);
    }
}

