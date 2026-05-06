package com.devon.building.security;

import com.devon.building.entity.Building;
import com.devon.building.entity.User;
import com.devon.building.repository.BuildingRepository;
import com.devon.building.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("buildingSecurity")
@AllArgsConstructor
public class buildingSecurity {
    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;

    public boolean isOwner(Long buildingId) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Building building = buildingRepository.findById(buildingId)
                .orElse(null);
        if (building == null || building.getUsers() == null) {
            return false;
        }
        return building.getUsers().contains(user);
    }
}
