package com.devon.building.repository.impl;

import com.devon.building.entity.AssignmentBuilding;
import com.devon.building.entity.Building;
import com.devon.building.entity.User;
import com.devon.building.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AssignmentBuildingRepositoryImpl implements AssignmentBuildingRepositoryCustom {
    @Override
    public List<AssignmentBuilding> getUpdateAssignmentBuilding(List<User> users, Building building) {
        List<AssignmentBuilding> assignmentBuildings = new ArrayList<>();
        for (User u : users)
        {
            AssignmentBuilding assignmentBuilding = new AssignmentBuilding();
            assignmentBuilding.setBuilding(building);
            assignmentBuilding.setUser(u);
            assignmentBuildings.add(assignmentBuilding);
        }
        return assignmentBuildings;
    }
}
