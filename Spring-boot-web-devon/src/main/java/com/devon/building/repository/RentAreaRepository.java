package com.devon.building.repository;

import com.devon.building.entity.Building;
import com.devon.building.entity.RentArea;
import com.devon.building.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentArea,Long>, RentAreaRepositoryCustom {
    void deleteByBuildingId(Long buildingId);
}
