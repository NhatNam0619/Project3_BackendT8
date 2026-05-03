package com.devon.building.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {
    private Long id;
    private String fullName;

    @NotNull(message = "phone not be null")
    @Min(value = 1, message = "phone must >= 0")
    Long phone;

    private String email;
    private String companyname;
    private String demand;
    private String status;
}
