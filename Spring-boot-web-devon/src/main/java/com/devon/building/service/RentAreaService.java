package com.devon.building.service;

import com.devon.building.entity.Building;
import com.devon.building.model.dto.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RentAreaService {
    //void updateRentArea(List<Long> rentArea, Building building );
    void updateRentArea(String rentArea, BuildingDTO building );
    void deleateRentArea(Long id);

}
