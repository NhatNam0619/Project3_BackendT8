package com.devon.building.service;

import com.devon.building.entity.Building;
import com.devon.building.model.dto.*;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.pagination.PaginationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {
    PaginationResult<BuildingResponseDTO> listBuildingsInfo(BuildingSearchRequest params, int page, int maxResult, int maxNavigationPage);
    List<BuildingResponseDTO> findAll(BuildingSearchRequest params);
    List<BuildingResponseDTO> listBuildings(List<Long> buildingId);
    List<StaffResponseDTO> listStaffs(Long buildingid) ;
    Building getBuiding (long id);
    BuildingDTO getBuidingsDTO (long id);
    BuildingDTO saveBuilding(BuildingDTO buildingDTO);
    BuildingDTO updateAssignmentBuilding(StaffIdsDTO staffIdsDTO);
    ResponseDTO deleteBuilding(List<Long> ids);
}
