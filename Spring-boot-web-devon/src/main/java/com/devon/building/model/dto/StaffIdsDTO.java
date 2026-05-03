package com.devon.building.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffIdsDTO {
    @NotNull(message = "id not be null")
    private Long id;
    List<Long> staffids;
    //List<User> users;
}
