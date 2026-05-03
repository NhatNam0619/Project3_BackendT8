package com.devon.building.service.impl;

import com.devon.building.builder.BuildingResquestBuilder;
import com.devon.building.converter.BuildingDTOsConverter;
import com.devon.building.converter.BuildingRequestBuilderConverter;
import com.devon.building.converter.BuildingResponseDTOsConvert;
import com.devon.building.converter.StaffResponseDTOsConverter;
import com.devon.building.entity.Building;
import com.devon.building.entity.User;
import com.devon.building.model.dto.*;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.pagination.PaginationResult;
import com.devon.building.repository.BuildingRepository;
import com.devon.building.repository.UserRepository;
import com.devon.building.service.BuildingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;
    private final BuildingRequestBuilderConverter buildingRequestBuilderConverter;
    private final BuildingResponseDTOsConvert buildingResponseDTOsConvert;
    private final StaffResponseDTOsConverter staffResponseDTOsConverter;
    private final BuildingDTOsConverter buildingDTOsConverter;
    private final ModelMapper modelMapper;

    @Override
    public PaginationResult<BuildingResponseDTO> listBuildingsInfo(BuildingSearchRequest params, int page, int maxResult, int maxNavigationPage) {
        BuildingResquestBuilder buildingResquestBuilder = buildingRequestBuilderConverter.toBuildingSearchBuilder(params);
        PaginationResult<Building> buildings = buildingRepository.findAll(buildingResquestBuilder,page, maxResult, maxNavigationPage);
        PaginationResult<BuildingResponseDTO> buildingResponseDTOS =  new PaginationResult<>();
        modelMapper.map(buildings, buildingResponseDTOS);
        List<BuildingResponseDTO> listBuildingResponseDTO = new ArrayList<>();
        for(Building building : buildings.getList())
        {
            BuildingResponseDTO buildingResponseDTO = buildingResponseDTOsConvert.convertBuildingResponseDTO(building);
            listBuildingResponseDTO.add(buildingResponseDTO);
        }
        buildingResponseDTOS.setList(listBuildingResponseDTO);
        return buildingResponseDTOS;
    }

    @Override
    public List<BuildingResponseDTO> findAll(BuildingSearchRequest params) {
        BuildingResquestBuilder buildingResquestBuilder = buildingRequestBuilderConverter.toBuildingSearchBuilder(params);
        List<Building> buildings = buildingRepository.findAll(buildingResquestBuilder);
        List<BuildingResponseDTO> buildingResponseDTOS = new ArrayList<>();
        for(Building building : buildings)
        {
            BuildingResponseDTO buildingResponseDTO = buildingResponseDTOsConvert.convertBuildingResponseDTO(building);
            buildingResponseDTOS.add(buildingResponseDTO);
        }
        return buildingResponseDTOS;
    }

    @Override
    public List<BuildingResponseDTO> listBuildings(List<Long> buildingId) {
        List<Building> buildings = buildingRepository.findAllById(buildingId);
        List<BuildingResponseDTO> buildingResponseDTOS = new ArrayList<>();
        for(Building b: buildings)
        {
            BuildingResponseDTO buildingResponseDTO = buildingResponseDTOsConvert.convertBuildingResponseDTO(b);
            buildingResponseDTOS.add(buildingResponseDTO);
        }
        return buildingResponseDTOS;
    }

    @Override
    public List<StaffResponseDTO> listStaffs(Long buildingid) {
        List<User> allStaff = userRepository.findByUserRoleAndActive("ROLE_STAFF",true);
        Building building = buildingRepository.findById(buildingid).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + buildingid));
        List<User> assignmentUser = building.getUsers();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(User staff: allStaff)
        {
            StaffResponseDTO staffResponseDTO = staffResponseDTOsConverter.convertStaffResponseDTO(staff);
            if(assignmentUser.contains(staff))
            {
                staffResponseDTO.setCheckbox("checked");
            }
            else {
                staffResponseDTO.setCheckbox("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
    return staffResponseDTOS;
    }

    @Override
    public Building getBuiding(long id) {
        return buildingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + id));
    }

    @Override
    public BuildingDTO getBuidingsDTO(long id) {
        return buildingDTOsConverter.toBuildingsDTO(buildingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + id)));
    }

    @Override
    public BuildingDTO saveBuilding(BuildingDTO buildingDTO) {
        Building building = buildingDTOsConverter.toBuildingsEntity(buildingDTO);
        buildingRepository.save(building);
        return buildingDTOsConverter.toBuildingsDTO(building);
    }


    @Override
    public BuildingDTO updateAssignmentBuilding(StaffIdsDTO staffIdsDTO) {
        Building building = buildingRepository.findById(staffIdsDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + staffIdsDTO.getId()));
        List<User> users = userRepository.findByIdIn(staffIdsDTO.getStaffids());
        building.setUsers(users);
        return buildingDTOsConverter.toBuildingsDTO(buildingRepository.findById(staffIdsDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + staffIdsDTO.getId())));
    }

    @Override
    public ResponseDTO deleteBuilding(List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        for (Long id : ids) {
            Building building = buildingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + id));
            if (!building.getUsers().isEmpty()) {
                responseDTO.setMessage("Tòa nhà đang được giao cho nv !!");
                return responseDTO;
            }
            if (building == null) {
                responseDTO.setMessage("Hãy chọn tòa nhà cần xóa !!");
                return responseDTO;
            }
            else {
                buildingRepository.deleteById(id);
            }
        }
        responseDTO.setMessage("Xoa Thanh Cong" );
        return responseDTO;
    }


}
