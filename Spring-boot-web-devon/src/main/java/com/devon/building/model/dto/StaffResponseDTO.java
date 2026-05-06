package com.devon.building.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffResponseDTO {
    Long id;
    String fullname;
    String checkbox;
    String username;
}
