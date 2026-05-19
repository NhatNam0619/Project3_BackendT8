package com.devon.building.model.request;

import com.devon.building.entity.User;
import com.devon.building.model.dto.AbstractDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerSearchRequest extends AbstractDTO {
    private String fullName;
    private String phone;
    private String email;
    private Long staffid;
    private String status;
    User user;
}
