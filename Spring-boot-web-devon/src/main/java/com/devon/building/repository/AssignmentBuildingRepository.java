package com.devon.building.repository;

import com.devon.building.entity.AssignmentBuilding;
import com.devon.building.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuilding, Long> , AssignmentBuildingRepositoryCustom {
    List<AssignmentBuilding> findAllByBuildingId(Long id);
    void deleteAllByBuildingId(Long id);
}
