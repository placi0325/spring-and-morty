package com.codecool.springbackend.repository.model;

import com.codecool.springbackend.repository.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique=true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // Add fields for storing arrays of location and character IDs
    @ElementCollection
    @CollectionTable(name = "user_locations", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "location_id")
    private List<Integer> locationIds;

    @ElementCollection
    @CollectionTable(name = "user_characters", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "character_id")
    private List<Integer> characterIds;

    public List<Integer> getLocationIds() {
        return locationIds;
    }

    public List<Integer> getCharacterIds() {
        return characterIds;
    }

    // Add and remove functions for location IDs
    public void addLocationId(Integer locationId) {
        if (!locationIds.contains(locationId)) {
            locationIds.add(locationId);
        }
    }

    public boolean removeLocationId(Integer locationId) {
        return locationIds.remove(locationId);
    }

    // Add and remove functions for character IDs
    public void addCharacterId(Integer characterId) {
        if (!characterIds.contains(characterId)) {
            characterIds.add(characterId);
        }
    }

    public boolean removeCharacterId(Integer characterId) {
        return characterIds.remove(characterId);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
