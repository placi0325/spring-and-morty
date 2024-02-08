package com.codecool.springbackend.service;

import com.codecool.springbackend.controller.dto.NewUserDTO;
import com.codecool.springbackend.repository.Role;
import com.codecool.springbackend.repository.UserRepository;
import com.codecool.springbackend.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Map.Entry<Integer, Long>> getFiveFavoriteCharacters() {
        List<Map<String, Object>> topCharactersWithLikes = userRepository.findTopFiveFavoriteCharactersWithLikes();

        return topCharactersWithLikes.stream()
                .map(entry -> Map.entry((Integer) entry.get("character_id"), (Long) entry.get("like_count")))
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean userEmailExists(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public void addNewUser(NewUserDTO newUserDTO) {
        if (userEmailExists(newUserDTO.email())) {
            throw new DataIntegrityViolationException("User with email already exists.");
        } else {
            User newUser = User.builder()
                    .email(newUserDTO.email())
                    .password(passwordEncoder.encode(newUserDTO.password()))
                    .role(Role.USER)
                    .build();
            userRepository.save(newUser);
        }
    }

    public void changePassword(Integer id, String newPassword) {
        Optional<User> user = getUserById(id);
        user.get().setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user.get());
    }

    public boolean updateEmail(Integer id, String newEmail) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public boolean updateRole(Integer id, Role newRole) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().setRole(newRole);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public boolean addLocationId(Integer id, Integer locationId) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().addLocationId(locationId);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public boolean removeLocationId(Integer id, Integer locationId) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            boolean removed = user.get().removeLocationId(locationId);
            if (removed) {
                userRepository.save(user.get());
            }
            return removed;
        }
        return false;
    }

    public boolean addCharacterId(Integer id, Integer characterId) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().addCharacterId(characterId);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public boolean removeCharacterId(Integer id, Integer characterId) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            boolean removed = user.get().removeCharacterId(characterId);
            if (removed) {
                userRepository.save(user.get());
            }
            return removed;
        }
        return false;
    }
}
