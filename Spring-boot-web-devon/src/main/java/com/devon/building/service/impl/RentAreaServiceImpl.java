package com.devon.building.service.impl;

import com.devon.building.converter.BuildingDTOsConverter;
import com.devon.building.entity.Building;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.repository.RentAreaRepository;
import com.devon.building.service.RentAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class RentAreaServiceImpl implements RentAreaService {

    private final RentAreaRepository rentAreaRepository;
    private final BuildingDTOsConverter buildingDTOsConverter;

    @Override
    public void updateRentArea(String rentArea, BuildingDTO buildingDTO ) {
        Building building = buildingDTOsConverter.toBuildingsEntity(buildingDTO);
        List<Long> areas = Arrays.stream(buildingDTO.getRentarea().split(",")).map(Long::parseLong).toList();
        if (rentArea != null && !rentArea.isEmpty()) {
            rentAreaRepository.deleteByBuildingId(building.getId());}
        rentAreaRepository.saveAll(rentAreaRepository.updateRentAreaBy(areas,building));
    }

    @Override
    public void deleateRentArea(Long id) {
        rentAreaRepository.deleteByBuildingId(id);
    }
}
