package com.codecool.springbackend.controller;

import com.codecool.springbackend.controller.dto.NewPasswordDTO;
import com.codecool.springbackend.controller.dto.NewUserDTO;
import com.codecool.springbackend.repository.model.User;
import com.codecool.springbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers(){return userService.getAllUsers();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody NewPasswordDTO newPasswordDTO){
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(passwordEncoder.matches(newPasswordDTO.oldPassword(), user.get().getPassword())){
            userService.changePassword(user.get().getId(), newPasswordDTO.newPassword());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Old password not matching");
        }
    }
}
