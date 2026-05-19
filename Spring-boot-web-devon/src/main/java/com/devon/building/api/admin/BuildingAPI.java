package com.devon.building.api.admin;

import com.devon.building.model.dto.*;
import com.devon.building.service.BuildingService;
import com.devon.building.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
public class BuildingAPI {

    private final BuildingService buildingService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{buildingId}/staffs")
    public ResponseEntity<ResponseDTO> loadStaffs(@PathVariable Long buildingId)
    {
        List<StaffResponseDTO> staffResponseDTOS = buildingService.listStaffs(buildingId);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(responseDTO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{buildingId}/buildings")
    public ResponseEntity<ResponseDTO> loadBuildings(@PathVariable List<Long> buildingId)
    {
        List<BuildingResponseDTO> buildings = buildingService.listBuildings(buildingId);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(buildings);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(responseDTO);
    }

    @PreAuthorize("hasRole('MANAGER') or @buildingSecurity.isOwner(#p0.id)")
    @PutMapping
    public ResponseEntity<ResponseDTO> updateBuilding(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if(bindingResult.hasErrors())
            {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            BuildingDTO buildingEntity = buildingService.saveBuilding(buildingDTO);
            responseDTO.setData(buildingEntity);
            responseDTO.setMessage("Done!!");
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createBuilding(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if(bindingResult.hasErrors())
            {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            BuildingDTO buildingEntity = buildingService.saveBuilding(buildingDTO);
            responseDTO.setData(buildingEntity);
            responseDTO.setMessage("Done!!");
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/assignmentbuilding")
    public ResponseEntity<BuildingDTO> updateAssignmentBuilding(@RequestBody StaffIdsDTO staffIdsDTO)
    {
        BuildingDTO buildingDTO = buildingService.updateAssignmentBuilding(staffIdsDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(buildingDTO);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(buildingDTO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{ids}")
    public ResponseEntity<ResponseDTO> deleteBuilding(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = buildingService.deleteBuilding(ids);
        return ResponseEntity.ok(responseDTO);
    }
}
