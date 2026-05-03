package com.devon.building.model.dto;

import com.devon.building.entity.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponeDTO extends AbstractDTO{
    private Long id;
    private String code;
    private String note;
    private Long staffid;
    Customer customer;

}
