package com.codecool.springbackend.repository;

import com.codecool.springbackend.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);

    @Query(value = "SELECT character_id, COUNT(*) AS like_count " +
            "FROM user_characters " +
            "GROUP BY character_id " +
            "ORDER BY like_count DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> findTopFiveFavoriteCharactersWithLikes();
}
