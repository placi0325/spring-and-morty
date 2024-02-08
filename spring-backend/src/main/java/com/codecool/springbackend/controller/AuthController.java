package com.codecool.springbackend.controller;

import com.codecool.springbackend.controller.dto.UserDTO;
import com.codecool.springbackend.repository.model.User;
import com.codecool.springbackend.security.JwtService;
import com.codecool.springbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService tokenService;
    private final UserService userService;

    @PostMapping("/login-user")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        Optional<User> user = userService.getUserByEmail(userDTO.email());
        if(user.isEmpty()){
            //no username found
            return new ResponseEntity<Error>(HttpStatusCode.valueOf(404));
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(user);
        } catch (AuthenticationException exception) {
            //bad password
            return new ResponseEntity<Error>(HttpStatusCode.valueOf(401));
        }
    }
}
