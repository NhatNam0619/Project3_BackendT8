package com.devon.building.converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.devon.building.entity.Building;
import com.devon.building.entity.RentArea;
import com.devon.building.model.dto.BuildingDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class BuildingDTOsConverter {
    private final ModelMapper modelMapper;

    public BuildingDTO toBuildingsDTO(Building it) {
        BuildingDTO buildingDTO = modelMapper.map(it, BuildingDTO.class);
        buildingDTO.setType(Arrays.asList(it.getType().split(",")));
        List<RentArea> rentAreas = it.getRentAreas();
        buildingDTO.setRentarea(rentAreas.stream().map(RentArea::getValue).sorted().map(String::valueOf).collect(Collectors.joining(",")));
        return buildingDTO;
    }
    public Building toBuildingsEntity(BuildingDTO buildingDTO) {
        Building building = modelMapper.map(buildingDTO, Building.class);
        building.setType(buildingDTO.getType().stream()
                .collect(Collectors.joining(",")));
        List<Long> listRentArea = Arrays.stream(buildingDTO.getRentarea().split(",")).map(Long::parseLong).toList();
        List<RentArea> rentAreas = new ArrayList<>();
        for(Long it : listRentArea)
        {
            RentArea rentArea = new RentArea();
            rentArea.setValue(it);
            rentArea.setBuilding(building);
            rentAreas.add(rentArea);
        }
        building.setRentAreas(rentAreas);
        return building;
    }
}
