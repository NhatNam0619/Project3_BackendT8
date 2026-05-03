package com.devon.building.service.impl;

import com.devon.building.entity.AssignmentBuilding;
import com.devon.building.entity.Building;
import com.devon.building.entity.User;
import com.devon.building.model.dto.StaffIdsDTO;
import com.devon.building.repository.AssignmentBuildingRepository;
import com.devon.building.service.AssignmentBuildingService;
import com.devon.building.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    UserService userService;
    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public List<AssignmentBuilding> getStaffs(Long id) {
        return assignmentBuildingRepository.findAllByBuildingId(id);
    }

    @Override
    public void updateAssignmentBuilding(StaffIdsDTO staffIdsDTO, Building building) {
        assignmentBuildingRepository.deleteAllByBuildingId(staffIdsDTO.getId());
        List<User> users = userService.getStaffsBuilding(staffIdsDTO.getStaffids());
        List<AssignmentBuilding> assignmentBuildings = assignmentBuildingRepository.getUpdateAssignmentBuilding(users,building);
        assignmentBuildingRepository.saveAll(assignmentBuildings);
    }

    @Override
    public void deleteAllBuildingId(Long id) {
        assignmentBuildingRepository.deleteAllByBuildingId(id);
    }
}
