package com.codecool.springbackend.controller;

import com.codecool.springbackend.controller.dto.NewUserDTO;
import com.codecool.springbackend.dao.model.User;
import com.codecool.springbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){return userService.getAllUsers();}

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody NewUserDTO userDTO){
        String email = userDTO.email();
        String password = userDTO.password();
        if(email.equals("") || password.equals("")){
            return ResponseEntity.badRequest().build();
        }
        userService.addNewUser(userDTO);
        return ResponseEntity.ok().build();
    }
}
