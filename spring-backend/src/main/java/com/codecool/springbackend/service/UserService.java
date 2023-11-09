package com.codecool.springbackend.service;

import com.codecool.springbackend.controller.dto.NewUserDTO;
import com.codecool.springbackend.repository.Role;
import com.codecool.springbackend.repository.UserRepository;
import com.codecool.springbackend.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
         return userRepository.getReferenceById(id);
    }

    public void addNewUser(NewUserDTO newUserDTO){
        User newUser = User.builder()
                .email(newUserDTO.username())
                .password(passwordEncoder.encode(newUserDTO.password()))
                .role(Role.USER)
                .build();
        userRepository.save(newUser);
    }
}
