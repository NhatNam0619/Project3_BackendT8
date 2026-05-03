package com.devon.building.repository.custom;

import com.devon.building.builder.BuildingResquestBuilder;
import com.devon.building.entity.Building;
import com.devon.building.model.dto.BuildingResponseDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.pagination.PaginationResult;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepositoryCustom {
    PaginationResult<Building> findAll(BuildingResquestBuilder params, int page, int maxResult, int maxNavigationPage);
    List<Building> findAll(BuildingResquestBuilder params);
}
