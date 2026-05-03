package com.devon.building.converter;
import java.util.List;
import java.util.stream.Collectors;
import com.devon.building.entity.Building;
import com.devon.building.entity.RentArea;
import com.devon.building.enums.District;
import com.devon.building.model.dto.BuildingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class BuildingResponseDTOsConvert {

    private final ModelMapper modelMapper;


    public BuildingResponseDTO convertBuildingResponseDTO(Building it) {
        BuildingResponseDTO buildingResponseDTO = modelMapper.map(it, BuildingResponseDTO.class);
        buildingResponseDTO.setAddress(String.join(", ",
                it.getStreet(),
                it.getWard(),
                District.valueOf(it.getDistrict()).getName()));
        List<RentArea> rentAreas = it.getRentAreas();
        String result = rentAreas.stream().map(RentArea::getValue).sorted().map(String::valueOf).collect(Collectors.joining("; "));
        buildingResponseDTO.setRentarea(result);
        return buildingResponseDTO;
    }
}
