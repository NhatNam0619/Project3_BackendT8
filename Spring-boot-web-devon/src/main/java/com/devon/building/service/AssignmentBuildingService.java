package com.devon.building.service;

import com.devon.building.entity.AssignmentBuilding;
import com.devon.building.entity.Building;
import com.devon.building.model.dto.StaffIdsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentBuildingService {
    List<AssignmentBuilding> getStaffs(Long id);
    void updateAssignmentBuilding(StaffIdsDTO staffIdsDTO, Building building);
    void deleteAllBuildingId(Long id);
}
