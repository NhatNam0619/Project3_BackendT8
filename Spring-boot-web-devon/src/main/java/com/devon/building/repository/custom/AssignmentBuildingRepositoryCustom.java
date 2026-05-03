package com.devon.building.repository.custom;

import com.devon.building.entity.AssignmentBuilding;
import com.devon.building.entity.Building;
import com.devon.building.entity.User;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
    List<AssignmentBuilding> getUpdateAssignmentBuilding(List<User> users, Building building);
}
