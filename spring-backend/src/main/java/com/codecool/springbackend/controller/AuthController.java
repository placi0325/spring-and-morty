package com.codecool.springbackend.controller;

import com.codecool.springbackend.controller.dto.NewUserDTO;
import com.codecool.springbackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService tokenService;

    @PostMapping("/login-user")
    public ResponseEntity<?> login(@RequestBody NewUserDTO userDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
        } catch (UsernameNotFoundException exception) {
            //no username found
            return new ResponseEntity<Error>(HttpStatusCode.valueOf(404));
        } catch (AuthenticationException exception) {
            //bad password
            return new ResponseEntity<Error>(HttpStatusCode.valueOf(401));
        }
    }
}
