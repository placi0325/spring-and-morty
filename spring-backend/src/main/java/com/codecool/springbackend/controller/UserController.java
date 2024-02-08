package com.codecool.springbackend.controller;

import com.codecool.springbackend.controller.dto.*;
import com.codecool.springbackend.repository.Role;
import com.codecool.springbackend.repository.model.User;
import com.codecool.springbackend.security.JwtService;
import com.codecool.springbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/five-favorite-characters")
    public ResponseEntity<List<Map.Entry<Integer, Long>>> getFiveFavoriteCharacters() {
        List<Map.Entry<Integer, Long>> favoriteCharacters = userService.getFiveFavoriteCharacters();
        return ResponseEntity.ok(favoriteCharacters);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody NewUserDTO userDTO) {
        String email = userDTO.email();
        String password = userDTO.password();
        if (email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        }
        try {
            userService.addNewUser(userDTO);
        } catch (DataIntegrityViolationException exception){
            //if user already exists by email
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email already exists.");
        }
        return ResponseEntity.ok().body("User added successfully.");
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader HttpHeaders headers, @RequestBody NewPasswordDTO newPasswordDTO) {

        String token = headers.getFirst("Authorization");
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        if ( passwordEncoder.matches( newPasswordDTO.oldPassword(), user.get().getPassword())) {
            userService.changePassword(user.get().getId(), newPasswordDTO.newPassword());
            return ResponseEntity.ok().body("Password changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Old password does not match.");
        }
    }

    @PatchMapping("/change-email")
    public ResponseEntity<?> changeEmail(@RequestHeader HttpHeaders headers, @RequestBody UserEmailDTO userEmailDTO) {
        String token = headers.getFirst("Authorization");
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        boolean updated = userService.updateEmail(user.get().getId(), userEmailDTO.newEmail());

        if (updated) {
            return ResponseEntity.ok().body("Email updated successfully.");
        } else {
            return new ResponseEntity<>("Failed to update email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/change-role")
    public ResponseEntity<?> changeRole(@RequestHeader HttpHeaders headers, @RequestBody UserRoleDTO userRoleDTO) {
        String token = headers.getFirst("Authorization");
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        // Check if the user submitting the request is an admin
        if (!user.get().getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can update user roles.");
        }

        boolean updated = userService.updateRole(userRoleDTO.userIDToUpdate(), Role.valueOf(userRoleDTO.newRole()));
        if (updated) {
            return ResponseEntity.ok().body("Role updated successfully.");
        } else {
            return new ResponseEntity<>("Failed to update role.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/add-favorite-location")
    public ResponseEntity<?> addLocation(@RequestHeader HttpHeaders headers,  @RequestBody UserLocationId userLocationId) {
        String token = headers.getFirst("Authorization");
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        boolean added = userService.addLocationId(user.get().getId(), Integer.valueOf(userLocationId.locationId()));
        if (added) {
            return ResponseEntity.ok().body("Location ID added successfully.");
        } else {
            return new ResponseEntity<>("Failed to add location ID.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove-favorite-location")
    public ResponseEntity<?> removeLocation(@RequestHeader HttpHeaders headers, @RequestBody UserLocationId userLocationId) {
        String token = headers.getFirst("Authorization");
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        boolean removed = userService.removeLocationId(user.get().getId(), Integer.valueOf(userLocationId.locationId()));
        if (removed) {
            return ResponseEntity.ok().body("Location ID removed successfully.");
        } else {
            return new ResponseEntity<>("Failed to remove location ID.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/add-favorite-character")
    public ResponseEntity<?> addCharacter(@RequestHeader HttpHeaders headers, @RequestBody UserCharacterId userCharacterId) {
        String token = headers.getFirst("Authorization");
        System.out.println(token);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        boolean added = userService.addCharacterId(user.get().getId(), Integer.valueOf(userCharacterId.characterId()));
        if (added) {
            return ResponseEntity.ok().body("Character ID added successfully.");
        } else {
            return new ResponseEntity<>("Failed to add character ID.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/remove-favorite-character")
    public ResponseEntity<?> removeCharacter(@RequestHeader HttpHeaders headers, @RequestBody UserCharacterId userCharacterId) {
        String token = headers.getFirst("Authorization");
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }

        boolean removed = userService.removeCharacterId(user.get().getId(), Integer.valueOf(userCharacterId.characterId()));
        if (removed) {
            return ResponseEntity.ok().body("Character ID removed successfully.");
        } else {
            return new ResponseEntity<>("Failed to remove character ID.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
