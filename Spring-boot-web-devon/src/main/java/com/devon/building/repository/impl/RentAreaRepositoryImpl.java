package com.devon.building.repository.impl;

import com.devon.building.entity.Building;
import com.devon.building.entity.RentArea;
import com.devon.building.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @Override
    public List<RentArea> updateRentAreaBy(List<Long> listRentArea, Building building) {
        List<RentArea> rentAreas = new ArrayList<>();
        for(Long it : listRentArea)
        {
            RentArea rentArea = new RentArea();
            rentArea.setValue(it);
            rentArea.setBuilding(building);
            rentAreas.add(rentArea);
        }
        return rentAreas;
    }
}
