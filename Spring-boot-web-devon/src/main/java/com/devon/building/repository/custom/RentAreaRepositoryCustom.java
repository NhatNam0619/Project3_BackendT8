package com.devon.building.repository.custom;

import com.devon.building.entity.Building;
import com.devon.building.entity.RentArea;

import java.util.List;

public interface RentAreaRepositoryCustom {
    List<RentArea> updateRentAreaBy(List<Long> rentAreas, Building building);
}
