package com.devon.building.converter;

import com.devon.building.entity.User;
import com.devon.building.model.dto.StaffResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StaffResponseDTOsConverter {

    public StaffResponseDTO convertStaffResponseDTO(User it)
    {
        StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
        staffResponseDTO.setId(it.getId());
        staffResponseDTO.setFullname(it.getFullName());
        staffResponseDTO.setUsername(it.getUserName());
        return staffResponseDTO;
    }
}
