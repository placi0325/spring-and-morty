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

    public List<Integer> getFiveFavoriteCharacters() {
        List<User> allUsers = userRepository.findAll();

        // Count the occurrences of each character ID across all users
        Map<Integer, Long> characterLikesCount = allUsers.stream()
                .flatMap(user -> user.getCharacterIds().stream())
                .collect(Collectors.groupingBy(characterId -> characterId, Collectors.counting()));

        // Sort the character IDs by the number of likes (descending order)
        List<Integer> sortedCharacterIds = characterLikesCount.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .toList();

        // Extract the first five character IDs
        return sortedCharacterIds.stream()
                .limit(5)
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
