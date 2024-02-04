package com.codecool.springbackend.controller;

import com.codecool.springbackend.controller.dto.*;
import com.codecool.springbackend.repository.Role;
import com.codecool.springbackend.repository.model.User;
import com.codecool.springbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/five-favorite-characters")
    public ResponseEntity<List<Integer>> getFiveFavoriteCharacters() {
        List<Integer> favoriteCharacters = userService.getFiveFavoriteCharacters();
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
        return ResponseEntity.ok().body("User added successfully.");
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody NewPasswordDTO newPasswordDTO) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        if (passwordEncoder.matches(newPasswordDTO.oldPassword(), user.get().getPassword())) {
            userService.changePassword(user.get().getId(), newPasswordDTO.newPassword());
            return ResponseEntity.ok().body("Password changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Old password does not match.");
        }
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Integer id, @RequestBody UserEmailDTO userEmailDTO) {
        boolean updated = userService.updateEmail(id, userEmailDTO.newEmail());
        if (updated) {
            return ResponseEntity.ok().body("Email updated successfully.");
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<?> updateRole(@PathVariable Integer id, @RequestBody UserRoleDTO userRoleDTO) {
        Optional<User> user = userService.getUserById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }

        // Check if the user submitting the request is an admin
        if (!user.get().getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can update user roles.");
        }

        boolean updated = userService.updateRole(userRoleDTO.userIDToUpdate(), Role.valueOf(userRoleDTO.newRole()));
        if (updated) {
            return ResponseEntity.ok().body("Role updated successfully.");
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/addLocation")
    public ResponseEntity<?> addLocationId(@PathVariable Integer id, @RequestBody UserLocationId userLocationId) {
        boolean added = userService.addLocationId(id, Integer.valueOf(userLocationId.locationId()));
        if (added) {
            return ResponseEntity.ok().body("Location ID added successfully.");
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/removeLocation")
    public ResponseEntity<?> removeLocationId(@PathVariable Integer id, @RequestBody UserLocationId userLocationId) {
        boolean removed = userService.removeLocationId(id, Integer.valueOf(userLocationId.locationId()));
        if (removed) {
            return ResponseEntity.ok().body("Location ID removed successfully.");
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/addCharacter")
    public ResponseEntity<?> addCharacterId(@PathVariable Integer id, @RequestBody UserCharacterId userCharacterId) {
        boolean added = userService.addCharacterId(id, Integer.valueOf(userCharacterId.characterId()));
        if (added) {
            return ResponseEntity.ok().body("Character ID added successfully.");
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/removeCharacter")
    public ResponseEntity<?> removeCharacterId(@PathVariable Integer id, @RequestBody UserCharacterId userCharacterId) {
        boolean removed = userService.removeCharacterId(id, Integer.valueOf(userCharacterId.characterId()));
        if (removed) {
            return ResponseEntity.ok().body("Character ID removed successfully.");
        } else {
            return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
